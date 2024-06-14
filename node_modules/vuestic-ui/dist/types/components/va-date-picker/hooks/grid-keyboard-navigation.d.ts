import { Ref } from 'vue';
export declare const useGridKeyboardNavigation: ({ rowSize, start, end, onSelected, onFocusIndex, }: {
    rowSize: number;
    start?: number | Ref<number> | undefined;
    end?: number | Ref<number> | undefined;
    onFocusIndex?: number | Ref<number> | undefined;
    onSelected?: ((selectedValue: number) => any | null) | undefined;
}) => {
    focusedCellIndex: Ref<number>;
    containerAttributes: {
        onFocus: () => void;
        onKeydown: (e: KeyboardEvent) => void;
        onBlur: () => void;
        onMousedown: () => void;
        tabindex: number;
    };
};
