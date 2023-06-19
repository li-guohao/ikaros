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

// May contain unused imports in some cases
// @ts-ignore
import { EpisodeResource } from './episode-resource';

/**
 *
 * @export
 * @interface Episode
 */
export interface Episode {
	/**
	 *
	 * @type {number}
	 * @memberof Episode
	 */
	id?: number;
	/**
	 *
	 * @type {string}
	 * @memberof Episode
	 */
	name?: string;
	/**
	 *
	 * @type {string}
	 * @memberof Episode
	 */
	description?: string;
	/**
	 *
	 * @type {number}
	 * @memberof Episode
	 */
	sequence?: number;
	/**
	 *
	 * @type {Array<EpisodeResource>}
	 * @memberof Episode
	 */
	resources?: Array<EpisodeResource>;
	/**
	 *
	 * @type {number}
	 * @memberof Episode
	 */
	subject_id?: number;
	/**
	 *
	 * @type {string}
	 * @memberof Episode
	 */
	name_cn?: string;
	/**
	 *
	 * @type {string}
	 * @memberof Episode
	 */
	air_time?: string;
}