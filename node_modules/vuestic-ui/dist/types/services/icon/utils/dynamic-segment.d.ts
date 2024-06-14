/**
 * Returns object with param name from template and match value from str
 * @example
 * ```
 * "fa-phone", "fa-{icon code}" -> { 'icon code': 'phone' }
 * "fa4 clock-o", "fa4 {icon-code}-{suffix}" -> { 'icon-code': 'clock', 'suffix': 'o' }
 * "fa4 clock-o", "fa4 {icon-code}" -> { 'icon-code': 'clock-o' }
 * ```
 */
export declare const dynamicSegments: (str: string, template: string) => {};
/**
 * Returns true if str match template
 * @example
 *  ```
 * "fa-phone", "fa-{icon code}" -> true
 * "fa4 clock-o", "fa4 {icon-code}-{suffix}" -> true
 * "fa4 clock-o", "fa4 {icon-code}" -> false
 * ```
 */
export declare const isMatchDynamicSegments: (str: string, template: string) => boolean;
