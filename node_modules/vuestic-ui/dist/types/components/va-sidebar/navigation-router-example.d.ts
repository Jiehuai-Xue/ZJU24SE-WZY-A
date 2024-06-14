export declare const navigationRoutes: {
    root: {
        name: string;
        displayName: string;
    };
    routes: ({
        name: string;
        displayName: string;
        meta: {
            iconClass: string;
        };
        children?: undefined;
        disabled?: undefined;
    } | {
        name: string;
        displayName: string;
        meta: {
            iconClass: string;
        };
        children: {
            name: string;
            displayName: string;
        }[];
        disabled?: undefined;
    } | {
        name: string;
        displayName: string;
        meta: {
            iconClass: string;
        };
        disabled: boolean;
        children: ({
            name: string;
            displayName: string;
            children?: undefined;
        } | {
            name: string;
            displayName: string;
            children: {
                displayName: string;
                name: string;
            }[];
        })[];
    })[];
};
