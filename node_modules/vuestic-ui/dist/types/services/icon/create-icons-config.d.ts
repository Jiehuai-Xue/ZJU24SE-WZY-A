import { IconConfig, IconConfiguration } from './types';
/**
 * Helper allow you to create icons config with Vuestic defaults.
 * Alias should have `to` property which is the name of font.
 */
export declare const createIconsConfig: (config: {
    aliases?: IconConfiguration[];
    fonts?: IconConfiguration[];
}) => IconConfig;
