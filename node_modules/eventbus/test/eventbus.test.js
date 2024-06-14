var expect = require('expect.js'),
    EventBus = require('../index').EventBus;

describe('EventBus', function(){
    describe('Base', function() {

        var Accounts = {
            listeners: {
                create_user: function(user, callback) {
                    console.log('Created user: ' + user);
                    callback(null, user);
                }
            }
        };

        it('#on', function() {
            var bus = new EventBus(),
                event,
                listeners,
                listener;
            bus.on('create_user', Accounts.listeners.create_user, Accounts);
            expect(bus.hasListener('create_user')).to.be.ok();
            event = bus.events['create_user'];
            expect(event).to.be.ok();
            expect(event.isEvent).to.be.ok();
            listeners = event.listeners;
            expect(listeners).to.be.ok();
            expect(listeners.length).to.be(1);
            listener = listeners[0];
            expect(listener.fn).to.eql(Accounts.listeners.create_user);
            expect(listener.scope).to.eql(Accounts);
        });

        it('#un', function() {
            var bus = new EventBus(),
                event,
                listeners;
            bus.on('create_user', Accounts.listeners.create_user, Accounts);
            expect(bus.hasListener('create_user')).to.be.ok();
            event = bus.events['create_user'];
            expect(event).to.be.ok();
            listeners = event.listeners;
            expect(listeners).to.be.ok();
            expect(listeners.length).to.be(1);
            bus.un('create_user', Accounts.listeners.create_user, Accounts);
            expect(bus.hasListener('create_user')).to.be(false);
            expect(event.listeners.length).to.be(0);
        });

        it('#dispatch', function(done){
            var bus = new EventBus();
            var user = {name: 'taoyuan', email: 'torworx@gmail.com', password: '123456'};
            bus.on('create_user', Accounts.listeners.create_user);
            bus.dispatch('create_user', user,
                function(err, user_created){
                    expect(err).to.be(null);
                    expect(user).to.eql(user_created);
                    done();
                }
            );
        });
    })
})