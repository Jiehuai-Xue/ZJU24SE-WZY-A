var ovy = require('ovy'),
    Event = require('./event'),
    EventBus;

function wrap(ename, body) {
    return {
        id:uuid.v4(),
        timestamp:new Date(),
        ename:ename,
        body:body
    };
};

// Private Destroyable class which removes listeners
var ListenerRemover = function(eventbus) {

    // Passed a ListenerRemover: return it
    if (eventbus instanceof ListenerRemover) {
        return eventbus;
    }

    this.eventbus = eventbus;

    this.args = ovy.arrays.slice(arguments, 1);
};
ListenerRemover.prototype.destroy = function() {
    this.eventbus.un.apply(this.eventbus, this.args);
}

EventBus = ovy.define({

    // @private
    // Matches options property names within a listeners specification object  - property names which are never used as event names.
    eventOptionsRe : /^(?:scope|delay|buffer|single|stopEvent|preventDefault|stopPropagation|normalized|args|delegate|element|destroyable|vertical|horizontal|freezeEvent)$/,

    /**
     * @private
     * Initial suspended call count. Incremented when {@link #suspendEvents} is called, decremented when {@link #resumeEvents} is called.
     */
    eventsSuspended: 0,

    constructor: function() {
        var me = this;

        // The subclass may have already initialized it.
        if (!me.hasListeners) {
            me.hasListeners = {};
        }

        me.events = me.events || {};
    },

    addListener: function(ename, fn, scope, options) {
        var me = this,
            config, event, hasListeners,
            prevListenerCount = 0;

        // Object listener hash passed
        if (typeof ename !== 'string') {
            options = ename;
            for (ename in options) {
                if (options.hasOwnProperty(ename)) {
                    config = options[ename];
                    if (!me.eventOptionsRe.test(ename)) {
                        me.addListener(ename, config.fn || config, config.scope || options.scope, config.fn ? config : options);
                    }
                }
            }
            if (options && options.destroyable) {
                return new ListenerRemover(me, options);
            }
        }
        // String, function passed
        else {
            ename = ename.toLowerCase();
            event = me.events[ename];
            if (event && event.isEvent) {
                prevListenerCount = event.listeners.length;
            } else {
                me.events[ename] = event = new Event(me, ename);
            }
            //<debug>
            if (!fn) {
                throw new Error('No function passed for event ' + me.$className + '.' + ename);
            }
            //</debug>

            // Allow listeners: { click: 'onClick', scope: myObject }
            if (typeof fn === 'string') {
                //<debug>
                if (!(scope[fn] || me[fn])) {
                    throw new Error('No method named "' + fn + '"');
                }
                //</debug>
                fn = scope[fn] || me[fn];
            }
            event.addListener(fn, scope, options);

            // If a new listener has been added (Event.addListener rejects duplicates of the same fn+scope)
            // then increment the hasListeners counter
            if (event.listeners.length !== prevListenerCount) {
                hasListeners = me.hasListeners;
                if (hasListeners.hasOwnProperty(ename)) {
                    // if we already have listeners at this level, just increment the count...
                    ++hasListeners[ename];
                } else {
                    // otherwise, start the count at 1 (which hides whatever is in our prototype
                    // chain)...
                    hasListeners[ename] = 1;
                }
            }
            if (options && options.destroyable) {
                return new ListenerRemover(me, ename, fn, scope, options);
            }
        }
    },

    /**
     * Removes an event handler.
     *
     * @param {String} eventName The type of event the handler was associated with.
     * @param {Function} fn The handler to remove. **This must be a reference to the function passed into the
     * {@link #addListener} call.**
     * @param {Object} scope (optional) The scope originally specified for the handler. It must be the same as the
     * scope argument specified in the original call to {@link #addListener} or the listener will not be removed.
     */
    removeListener: function(ename, fn, scope) {
        var me = this,
            config,
            event,
            options;

        if (typeof ename !== 'string') {
            options = ename;
            for (ename in options) {
                if (options.hasOwnProperty(ename)) {
                    config = options[ename];
                    if (!me.eventOptionsRe.test(ename)) {
                        me.removeListener(ename, config.fn || config, config.scope || options.scope);
                    }
                }
            }
        } else {
            ename = ename.toLowerCase();
            event = me.events[ename];
            if (event && event.isEvent) {
                if (event.removeListener(fn, scope) && !--me.hasListeners[ename]) {
                    // Delete this entry, since 0 does not mean no one is listening, just
                    // that no one is *directly* listening. This allows the eventBus or
                    // class observers to "poke" through and expose their presence.
                    delete me.hasListeners[ename];
                }
            }
        }
    },

    /**
     * Removes all listeners for this object including the managed listeners
     */
    clearListeners: function() {
        var events = this.events,
            hasListeners = this.hasListeners,
            event,
            key;

        for (key in events) {
            if (events.hasOwnProperty(key)) {
                event = events[key];
                if (event.isEvent) {
                    delete hasListeners[key];
                    event.clearListeners();
                }
            }
        }
    },

    /**
     * Fires the specified event with the passed parameters (minus the event name, plus the `options` object passed
     * to {@link #addListener}).
     *
     * @param {String} eventName The name of the event to fire.
     * @param {Object...} args Variable number of parameters are passed to handlers.
     * @return {Boolean} returns false if any of the handlers return false otherwise it returns true.
     */
    dispatch: function(eventName) {
        eventName = eventName.toLowerCase();
        var me = this,
            events = me.events,
            event = events && events[eventName],
            ret = true;

        // Only continue firing the event if there are listeners to be informed.
        if (event && me.hasListeners[eventName]) {
            ret = me.continueDispatch(eventName, ovy.arrays.slice(arguments, 1));
        }
        return ret;
    },

    /**
     * Continue to fire event.
     * @private
     *
     * @param {String} eventName
     * @param {Array} args
     */
    continueDispatch: function(eventName, args) {
        var target = this,
            queue, event,
            ret = true;

        if (target.eventsSuspended) {
            if ((queue = target.eventQueue)) {
                queue.push([eventName, args]);
            }
            return ret;
        } else {
            event = target.events[eventName];
            if (event && event != true) {
                ret = event.fire.apply(event, args);
            }
        }
        return ret;
    },

    /**
     * Checks to see if this object has any listeners for a specified event, or whether the event bubbles. The answer
     * indicates whether the event needs firing or not.
     *
     * @param {String} eventName The name of the event to check for
     * @return {Boolean} `true` if the event is being listened for or bubbles, else `false`
     */
    hasListener: function(ename) {
        return !!this.hasListeners[ename.toLowerCase()];
    },

    /**
     * Suspends the firing of all events. (see {@link #resumeEvents})
     *
     * @param {Boolean} queueSuspended Pass as true to queue up suspended events to be fired
     * after the {@link #resumeEvents} call instead of discarding all suspended events.
     */
    suspendEvents: function(queueSuspended) {
        this.eventsSuspended += 1;
        if (queueSuspended && !this.eventQueue) {
            this.eventQueue = [];
        }
    },

    /**
     * Resumes firing events (see {@link #suspendEvents}).
     *
     * If events were suspended using the `queueSuspended` parameter, then all events fired
     * during event suspension will be sent to any listeners now.
     */
    resumeEvents: function() {
        var me = this,
            queued = me.eventQueue,
            qLen, q;

        if (me.eventsSuspended && ! --me.eventsSuspended) {
            delete me.eventQueue;

            if (queued) {
                qLen = queued.length;
                for (q = 0; q < qLen; q++) {
                    me.continueDispatch.apply(me, queued[q]);
                }
            }
        }
    }
});

ovy.functions.createAlias(EventBus, {
    /**
     * @method
     * Shorthand for {@link #addListener}.
     */
    on: 'addListener',
    /**
     * @method
     * Shorthand for {@link #removeListener}.
     */
    un: 'removeListener'
})

exports = module.exports = EventBus;