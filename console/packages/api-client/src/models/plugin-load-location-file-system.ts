/* tslint:disable */
/* eslint-disable */
/**
 * Ikaros Open API Documentation
 * Documentation for Ikaros Open API
 *
 * The version of the OpenAPI document: 1.0.0
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

/**
 *
 * @export
 * @interface PluginLoadLocationFileSystem
 */
export interface PluginLoadLocationFileSystem {
  /**
   *
   * @type {boolean}
   * @memberof PluginLoadLocationFileSystem
   */
  open?: boolean;
  /**
   *
   * @type {boolean}
   * @memberof PluginLoadLocationFileSystem
   */
  readOnly?: boolean;
  /**
   *
   * @type {string}
   * @memberof PluginLoadLocationFileSystem
   */
  separator?: string;
  /**
   *
   * @type {object}
   * @memberof PluginLoadLocationFileSystem
   */
  rootDirectories?: object;
  /**
   *
   * @type {object}
   * @memberof PluginLoadLocationFileSystem
   */
  fileStores?: object;
  /**
   *
   * @type {object}
   * @memberof PluginLoadLocationFileSystem
   */
  userPrincipalLookupService?: object;
}
