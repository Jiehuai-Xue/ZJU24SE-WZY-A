/**
 * Create and append to head provided styles or update if exists.
 *
 * @param id - used to identify block for removal etc
 * @param getStyles - closure to get styles
 */
export declare const addOrUpdateStyleElement: (id: string, getStyles: () => string) => void;
