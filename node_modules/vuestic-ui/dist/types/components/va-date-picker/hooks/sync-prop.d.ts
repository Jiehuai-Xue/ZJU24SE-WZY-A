import { Ref } from 'vue';
export declare function useSyncProp<T, PropName extends string>(propRef: Ref<T> | undefined, propName: PropName, emit: (event: any, newValue: T) => any, defaultValue?: T): {
    syncProp: import("vue").WritableComputedRef<T>;
};
