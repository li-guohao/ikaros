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

import type { Configuration } from '../configuration';
import type { AxiosPromise, AxiosInstance, AxiosRequestConfig } from 'axios';
import globalAxios from 'axios';
// Some imports not used depending on template conditions
// @ts-ignore
import {
	DUMMY_BASE_URL,
	assertParamExists,
	setApiKeyToObject,
	setBasicAuthToObject,
	setBearerAuthToObject,
	setOAuthToObject,
	setSearchParams,
	serializeDataIfNeeded,
	toPathString,
	createRequestFunction,
} from '../common';
// @ts-ignore
import {
	BASE_PATH,
	COLLECTION_FORMATS,
	RequestArgs,
	BaseAPI,
	RequiredError,
} from '../base';
// @ts-ignore
import { AttachmentRelation } from '../models';
/**
 * V1alpha1AttachmentRelationApi - axios parameter creator
 * @export
 */
export const V1alpha1AttachmentRelationApiAxiosParamCreator = function (
	configuration?: Configuration
) {
	return {
		/**
		 *
		 * @param {number} attachmentId Attachment ID
		 * @param {'VIDEO_SUBTITLE'} relationType Relation type
		 * @param {*} [options] Override http request option.
		 * @throws {RequiredError}
		 */
		findAttachmentRelations: async (
			attachmentId: number,
			relationType: 'VIDEO_SUBTITLE',
			options: AxiosRequestConfig = {}
		): Promise<RequestArgs> => {
			// verify required parameter 'attachmentId' is not null or undefined
			assertParamExists(
				'findAttachmentRelations',
				'attachmentId',
				attachmentId
			);
			// verify required parameter 'relationType' is not null or undefined
			assertParamExists(
				'findAttachmentRelations',
				'relationType',
				relationType
			);
			const localVarPath = `/api/v1alpha1/attachment/relations`;
			// use dummy base URL string because the URL constructor only accepts absolute URLs.
			const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
			let baseOptions;
			if (configuration) {
				baseOptions = configuration.baseOptions;
			}

			const localVarRequestOptions = {
				method: 'GET',
				...baseOptions,
				...options,
			};
			const localVarHeaderParameter = {} as any;
			const localVarQueryParameter = {} as any;

			// authentication BasicAuth required
			// http basic authentication required
			setBasicAuthToObject(localVarRequestOptions, configuration);

			// authentication BearerAuth required
			// http bearer authentication required
			await setBearerAuthToObject(localVarHeaderParameter, configuration);

			if (attachmentId !== undefined) {
				localVarQueryParameter['attachmentId'] = attachmentId;
			}

			if (relationType !== undefined) {
				localVarQueryParameter['relationType'] = relationType;
			}

			setSearchParams(localVarUrlObj, localVarQueryParameter);
			let headersFromBaseOptions =
				baseOptions && baseOptions.headers ? baseOptions.headers : {};
			localVarRequestOptions.headers = {
				...localVarHeaderParameter,
				...headersFromBaseOptions,
				...options.headers,
			};

			return {
				url: toPathString(localVarUrlObj),
				options: localVarRequestOptions,
			};
		},
	};
};

/**
 * V1alpha1AttachmentRelationApi - functional programming interface
 * @export
 */
export const V1alpha1AttachmentRelationApiFp = function (
	configuration?: Configuration
) {
	const localVarAxiosParamCreator =
		V1alpha1AttachmentRelationApiAxiosParamCreator(configuration);
	return {
		/**
		 *
		 * @param {number} attachmentId Attachment ID
		 * @param {'VIDEO_SUBTITLE'} relationType Relation type
		 * @param {*} [options] Override http request option.
		 * @throws {RequiredError}
		 */
		async findAttachmentRelations(
			attachmentId: number,
			relationType: 'VIDEO_SUBTITLE',
			options?: AxiosRequestConfig
		): Promise<
			(
				axios?: AxiosInstance,
				basePath?: string
			) => AxiosPromise<Array<AttachmentRelation>>
		> {
			const localVarAxiosArgs =
				await localVarAxiosParamCreator.findAttachmentRelations(
					attachmentId,
					relationType,
					options
				);
			return createRequestFunction(
				localVarAxiosArgs,
				globalAxios,
				BASE_PATH,
				configuration
			);
		},
	};
};

/**
 * V1alpha1AttachmentRelationApi - factory interface
 * @export
 */
export const V1alpha1AttachmentRelationApiFactory = function (
	configuration?: Configuration,
	basePath?: string,
	axios?: AxiosInstance
) {
	const localVarFp = V1alpha1AttachmentRelationApiFp(configuration);
	return {
		/**
		 *
		 * @param {V1alpha1AttachmentRelationApiFindAttachmentRelationsRequest} requestParameters Request parameters.
		 * @param {*} [options] Override http request option.
		 * @throws {RequiredError}
		 */
		findAttachmentRelations(
			requestParameters: V1alpha1AttachmentRelationApiFindAttachmentRelationsRequest,
			options?: AxiosRequestConfig
		): AxiosPromise<Array<AttachmentRelation>> {
			return localVarFp
				.findAttachmentRelations(
					requestParameters.attachmentId,
					requestParameters.relationType,
					options
				)
				.then((request) => request(axios, basePath));
		},
	};
};

/**
 * Request parameters for findAttachmentRelations operation in V1alpha1AttachmentRelationApi.
 * @export
 * @interface V1alpha1AttachmentRelationApiFindAttachmentRelationsRequest
 */
export interface V1alpha1AttachmentRelationApiFindAttachmentRelationsRequest {
	/**
	 * Attachment ID
	 * @type {number}
	 * @memberof V1alpha1AttachmentRelationApiFindAttachmentRelations
	 */
	readonly attachmentId: number;

	/**
	 * Relation type
	 * @type {'VIDEO_SUBTITLE'}
	 * @memberof V1alpha1AttachmentRelationApiFindAttachmentRelations
	 */
	readonly relationType: 'VIDEO_SUBTITLE';
}

/**
 * V1alpha1AttachmentRelationApi - object-oriented interface
 * @export
 * @class V1alpha1AttachmentRelationApi
 * @extends {BaseAPI}
 */
export class V1alpha1AttachmentRelationApi extends BaseAPI {
	/**
	 *
	 * @param {V1alpha1AttachmentRelationApiFindAttachmentRelationsRequest} requestParameters Request parameters.
	 * @param {*} [options] Override http request option.
	 * @throws {RequiredError}
	 * @memberof V1alpha1AttachmentRelationApi
	 */
	public findAttachmentRelations(
		requestParameters: V1alpha1AttachmentRelationApiFindAttachmentRelationsRequest,
		options?: AxiosRequestConfig
	) {
		return V1alpha1AttachmentRelationApiFp(this.configuration)
			.findAttachmentRelations(
				requestParameters.attachmentId,
				requestParameters.relationType,
				options
			)
			.then((request) => request(this.axios, this.basePath));
	}
}