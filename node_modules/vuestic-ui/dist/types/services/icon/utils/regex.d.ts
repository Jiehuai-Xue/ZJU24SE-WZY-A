export declare const isMatchRegex: (str: string, regex: RegExp) => boolean;
/**
 * Return values from regex groups
 * @example
 * ```
 * "fa-phone-o", /fa-(.*)-(.*)/ -> ["phone", "o"]
 * "any string", /(.*)/ -> ["any string"]
 * "global regex test", /global (regex) (test)/g -> [['regex', 'test']]
 * ```
 */
export declare const regexGroupsValues: (str: string, regex: RegExp | string) => string[] | string[][];
