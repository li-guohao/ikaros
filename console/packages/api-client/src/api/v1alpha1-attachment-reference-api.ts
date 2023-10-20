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
import { AttachmentReference } from '../models';
// @ts-ignore
import { BatchMatchingEpisodeAttachment } from '../models';
/**
 * V1alpha1AttachmentReferenceApi - axios parameter creator
 * @export
 */
export const V1alpha1AttachmentReferenceApiAxiosParamCreator = function (
	configuration?: Configuration
) {
	return {
		/**
		 *
		 * @param {number} id AttachmentReference ID
		 * @param {*} [options] Override http request option.
		 * @throws {RequiredError}
		 */
		deleteAttachmentReference: async (
			id: number,
			options: AxiosRequestConfig = {}
		): Promise<RequestArgs> => {
			// verify required parameter 'id' is not null or undefined
			assertParamExists('deleteAttachmentReference', 'id', id);
			const localVarPath = `/api/v1alpha1/attachment/reference/id`;
			// use dummy base URL string because the URL constructor only accepts absolute URLs.
			const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
			let baseOptions;
			if (configuration) {
				baseOptions = configuration.baseOptions;
			}

			const localVarRequestOptions = {
				method: 'DELETE',
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

			if (id !== undefined) {
				localVarQueryParameter['id'] = id;
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
		/**
		 *
		 * @param {'SUBJECT' | 'EPISODE'} type AttachmentReference type
		 * @param {number} attachmentId Attachment id
		 * @param {*} [options] Override http request option.
		 * @throws {RequiredError}
		 */
		findAllByTypeAndAttachmentId: async (
			type: 'SUBJECT' | 'EPISODE',
			attachmentId: number,
			options: AxiosRequestConfig = {}
		): Promise<RequestArgs> => {
			// verify required parameter 'type' is not null or undefined
			assertParamExists('findAllByTypeAndAttachmentId', 'type', type);
			// verify required parameter 'attachmentId' is not null or undefined
			assertParamExists(
				'findAllByTypeAndAttachmentId',
				'attachmentId',
				attachmentId
			);
			const localVarPath = `/api/v1alpha1/attachment/references`;
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

			if (type !== undefined) {
				localVarQueryParameter['type'] = type;
			}

			if (attachmentId !== undefined) {
				localVarQueryParameter['attachmentId'] = attachmentId;
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
		/**
		 * Matching attachments to episodes for single subject.
		 * @param {BatchMatchingEpisodeAttachment} batchMatchingEpisodeAttachment batch matching episodes and attachments request value object
		 * @param {*} [options] Override http request option.
		 * @throws {RequiredError}
		 */
		matchingAttachmentsAndSubjectEpisodes: async (
			batchMatchingEpisodeAttachment: BatchMatchingEpisodeAttachment,
			options: AxiosRequestConfig = {}
		): Promise<RequestArgs> => {
			// verify required parameter 'batchMatchingEpisodeAttachment' is not null or undefined
			assertParamExists(
				'matchingAttachmentsAndSubjectEpisodes',
				'batchMatchingEpisodeAttachment',
				batchMatchingEpisodeAttachment
			);
			const localVarPath = `/api/v1alpha1/attachment/references/subject/episodes`;
			// use dummy base URL string because the URL constructor only accepts absolute URLs.
			const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
			let baseOptions;
			if (configuration) {
				baseOptions = configuration.baseOptions;
			}

			const localVarRequestOptions = {
				method: 'POST',
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

			localVarHeaderParameter['Content-Type'] = 'application/json';

			setSearchParams(localVarUrlObj, localVarQueryParameter);
			let headersFromBaseOptions =
				baseOptions && baseOptions.headers ? baseOptions.headers : {};
			localVarRequestOptions.headers = {
				...localVarHeaderParameter,
				...headersFromBaseOptions,
				...options.headers,
			};
			localVarRequestOptions.data = serializeDataIfNeeded(
				batchMatchingEpisodeAttachment,
				localVarRequestOptions,
				configuration
			);

			return {
				url: toPathString(localVarUrlObj),
				options: localVarRequestOptions,
			};
		},
		/**
		 * Remove by type and attachmentId and referenceId
		 * @param {AttachmentReference} [attachmentReference]
		 * @param {*} [options] Override http request option.
		 * @throws {RequiredError}
		 */
		removeByTypeAndAttachmentIdAndReferenceId: async (
			attachmentReference?: AttachmentReference,
			options: AxiosRequestConfig = {}
		): Promise<RequestArgs> => {
			const localVarPath = `/api/v1alpha1/attachment/reference/uk`;
			// use dummy base URL string because the URL constructor only accepts absolute URLs.
			const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
			let baseOptions;
			if (configuration) {
				baseOptions = configuration.baseOptions;
			}

			const localVarRequestOptions = {
				method: 'DELETE',
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

			localVarHeaderParameter['Content-Type'] = 'application/json';

			setSearchParams(localVarUrlObj, localVarQueryParameter);
			let headersFromBaseOptions =
				baseOptions && baseOptions.headers ? baseOptions.headers : {};
			localVarRequestOptions.headers = {
				...localVarHeaderParameter,
				...headersFromBaseOptions,
				...options.headers,
			};
			localVarRequestOptions.data = serializeDataIfNeeded(
				attachmentReference,
				localVarRequestOptions,
				configuration
			);

			return {
				url: toPathString(localVarUrlObj),
				options: localVarRequestOptions,
			};
		},
		/**
		 * Save attachment reference.
		 * @param {AttachmentReference} [attachmentReference]
		 * @param {*} [options] Override http request option.
		 * @throws {RequiredError}
		 */
		saveAttachmentReference: async (
			attachmentReference?: AttachmentReference,
			options: AxiosRequestConfig = {}
		): Promise<RequestArgs> => {
			const localVarPath = `/api/v1alpha1/attachment/reference`;
			// use dummy base URL string because the URL constructor only accepts absolute URLs.
			const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
			let baseOptions;
			if (configuration) {
				baseOptions = configuration.baseOptions;
			}

			const localVarRequestOptions = {
				method: 'PUT',
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

			localVarHeaderParameter['Content-Type'] = 'application/json';

			setSearchParams(localVarUrlObj, localVarQueryParameter);
			let headersFromBaseOptions =
				baseOptions && baseOptions.headers ? baseOptions.headers : {};
			localVarRequestOptions.headers = {
				...localVarHeaderParameter,
				...headersFromBaseOptions,
				...options.headers,
			};
			localVarRequestOptions.data = serializeDataIfNeeded(
				attachmentReference,
				localVarRequestOptions,
				configuration
			);

			return {
				url: toPathString(localVarUrlObj),
				options: localVarRequestOptions,
			};
		},
	};
};

/**
 * V1alpha1AttachmentReferenceApi - functional programming interface
 * @export
 */
export const V1alpha1AttachmentReferenceApiFp = function (
	configuration?: Configuration
) {
	const localVarAxiosParamCreator =
		V1alpha1AttachmentReferenceApiAxiosParamCreator(configuration);
	return {
		/**
		 *
		 * @param {number} id AttachmentReference ID
		 * @param {*} [options] Override http request option.
		 * @throws {RequiredError}
		 */
		async deleteAttachmentReference(
			id: number,
			options?: AxiosRequestConfig
		): Promise<
			(axios?: AxiosInstance, basePath?: string) => AxiosPromise<void>
		> {
			const localVarAxiosArgs =
				await localVarAxiosParamCreator.deleteAttachmentReference(id, options);
			return createRequestFunction(
				localVarAxiosArgs,
				globalAxios,
				BASE_PATH,
				configuration
			);
		},
		/**
		 *
		 * @param {'SUBJECT' | 'EPISODE'} type AttachmentReference type
		 * @param {number} attachmentId Attachment id
		 * @param {*} [options] Override http request option.
		 * @throws {RequiredError}
		 */
		async findAllByTypeAndAttachmentId(
			type: 'SUBJECT' | 'EPISODE',
			attachmentId: number,
			options?: AxiosRequestConfig
		): Promise<
			(axios?: AxiosInstance, basePath?: string) => AxiosPromise<void>
		> {
			const localVarAxiosArgs =
				await localVarAxiosParamCreator.findAllByTypeAndAttachmentId(
					type,
					attachmentId,
					options
				);
			return createRequestFunction(
				localVarAxiosArgs,
				globalAxios,
				BASE_PATH,
				configuration
			);
		},
		/**
		 * Matching attachments to episodes for single subject.
		 * @param {BatchMatchingEpisodeAttachment} batchMatchingEpisodeAttachment batch matching episodes and attachments request value object
		 * @param {*} [options] Override http request option.
		 * @throws {RequiredError}
		 */
		async matchingAttachmentsAndSubjectEpisodes(
			batchMatchingEpisodeAttachment: BatchMatchingEpisodeAttachment,
			options?: AxiosRequestConfig
		): Promise<
			(axios?: AxiosInstance, basePath?: string) => AxiosPromise<void>
		> {
			const localVarAxiosArgs =
				await localVarAxiosParamCreator.matchingAttachmentsAndSubjectEpisodes(
					batchMatchingEpisodeAttachment,
					options
				);
			return createRequestFunction(
				localVarAxiosArgs,
				globalAxios,
				BASE_PATH,
				configuration
			);
		},
		/**
		 * Remove by type and attachmentId and referenceId
		 * @param {AttachmentReference} [attachmentReference]
		 * @param {*} [options] Override http request option.
		 * @throws {RequiredError}
		 */
		async removeByTypeAndAttachmentIdAndReferenceId(
			attachmentReference?: AttachmentReference,
			options?: AxiosRequestConfig
		): Promise<
			(axios?: AxiosInstance, basePath?: string) => AxiosPromise<void>
		> {
			const localVarAxiosArgs =
				await localVarAxiosParamCreator.removeByTypeAndAttachmentIdAndReferenceId(
					attachmentReference,
					options
				);
			return createRequestFunction(
				localVarAxiosArgs,
				globalAxios,
				BASE_PATH,
				configuration
			);
		},
		/**
		 * Save attachment reference.
		 * @param {AttachmentReference} [attachmentReference]
		 * @param {*} [options] Override http request option.
		 * @throws {RequiredError}
		 */
		async saveAttachmentReference(
			attachmentReference?: AttachmentReference,
			options?: AxiosRequestConfig
		): Promise<
			(axios?: AxiosInstance, basePath?: string) => AxiosPromise<void>
		> {
			const localVarAxiosArgs =
				await localVarAxiosParamCreator.saveAttachmentReference(
					attachmentReference,
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
 * V1alpha1AttachmentReferenceApi - factory interface
 * @export
 */
export const V1alpha1AttachmentReferenceApiFactory = function (
	configuration?: Configuration,
	basePath?: string,
	axios?: AxiosInstance
) {
	const localVarFp = V1alpha1AttachmentReferenceApiFp(configuration);
	return {
		/**
		 *
		 * @param {V1alpha1AttachmentReferenceApiDeleteAttachmentReferenceRequest} requestParameters Request parameters.
		 * @param {*} [options] Override http request option.
		 * @throws {RequiredError}
		 */
		deleteAttachmentReference(
			requestParameters: V1alpha1AttachmentReferenceApiDeleteAttachmentReferenceRequest,
			options?: AxiosRequestConfig
		): AxiosPromise<void> {
			return localVarFp
				.deleteAttachmentReference(requestParameters.id, options)
				.then((request) => request(axios, basePath));
		},
		/**
		 *
		 * @param {V1alpha1AttachmentReferenceApiFindAllByTypeAndAttachmentIdRequest} requestParameters Request parameters.
		 * @param {*} [options] Override http request option.
		 * @throws {RequiredError}
		 */
		findAllByTypeAndAttachmentId(
			requestParameters: V1alpha1AttachmentReferenceApiFindAllByTypeAndAttachmentIdRequest,
			options?: AxiosRequestConfig
		): AxiosPromise<void> {
			return localVarFp
				.findAllByTypeAndAttachmentId(
					requestParameters.type,
					requestParameters.attachmentId,
					options
				)
				.then((request) => request(axios, basePath));
		},
		/**
		 * Matching attachments to episodes for single subject.
		 * @param {V1alpha1AttachmentReferenceApiMatchingAttachmentsAndSubjectEpisodesRequest} requestParameters Request parameters.
		 * @param {*} [options] Override http request option.
		 * @throws {RequiredError}
		 */
		matchingAttachmentsAndSubjectEpisodes(
			requestParameters: V1alpha1AttachmentReferenceApiMatchingAttachmentsAndSubjectEpisodesRequest,
			options?: AxiosRequestConfig
		): AxiosPromise<void> {
			return localVarFp
				.matchingAttachmentsAndSubjectEpisodes(
					requestParameters.batchMatchingEpisodeAttachment,
					options
				)
				.then((request) => request(axios, basePath));
		},
		/**
		 * Remove by type and attachmentId and referenceId
		 * @param {V1alpha1AttachmentReferenceApiRemoveByTypeAndAttachmentIdAndReferenceIdRequest} requestParameters Request parameters.
		 * @param {*} [options] Override http request option.
		 * @throws {RequiredError}
		 */
		removeByTypeAndAttachmentIdAndReferenceId(
			requestParameters: V1alpha1AttachmentReferenceApiRemoveByTypeAndAttachmentIdAndReferenceIdRequest = {},
			options?: AxiosRequestConfig
		): AxiosPromise<void> {
			return localVarFp
				.removeByTypeAndAttachmentIdAndReferenceId(
					requestParameters.attachmentReference,
					options
				)
				.then((request) => request(axios, basePath));
		},
		/**
		 * Save attachment reference.
		 * @param {V1alpha1AttachmentReferenceApiSaveAttachmentReferenceRequest} requestParameters Request parameters.
		 * @param {*} [options] Override http request option.
		 * @throws {RequiredError}
		 */
		saveAttachmentReference(
			requestParameters: V1alpha1AttachmentReferenceApiSaveAttachmentReferenceRequest = {},
			options?: AxiosRequestConfig
		): AxiosPromise<void> {
			return localVarFp
				.saveAttachmentReference(requestParameters.attachmentReference, options)
				.then((request) => request(axios, basePath));
		},
	};
};

/**
 * Request parameters for deleteAttachmentReference operation in V1alpha1AttachmentReferenceApi.
 * @export
 * @interface V1alpha1AttachmentReferenceApiDeleteAttachmentReferenceRequest
 */
export interface V1alpha1AttachmentReferenceApiDeleteAttachmentReferenceRequest {
	/**
	 * AttachmentReference ID
	 * @type {number}
	 * @memberof V1alpha1AttachmentReferenceApiDeleteAttachmentReference
	 */
	readonly id: number;
}

/**
 * Request parameters for findAllByTypeAndAttachmentId operation in V1alpha1AttachmentReferenceApi.
 * @export
 * @interface V1alpha1AttachmentReferenceApiFindAllByTypeAndAttachmentIdRequest
 */
export interface V1alpha1AttachmentReferenceApiFindAllByTypeAndAttachmentIdRequest {
	/**
	 * AttachmentReference type
	 * @type {'SUBJECT' | 'EPISODE'}
	 * @memberof V1alpha1AttachmentReferenceApiFindAllByTypeAndAttachmentId
	 */
	readonly type: 'SUBJECT' | 'EPISODE';

	/**
	 * Attachment id
	 * @type {number}
	 * @memberof V1alpha1AttachmentReferenceApiFindAllByTypeAndAttachmentId
	 */
	readonly attachmentId: number;
}

/**
 * Request parameters for matchingAttachmentsAndSubjectEpisodes operation in V1alpha1AttachmentReferenceApi.
 * @export
 * @interface V1alpha1AttachmentReferenceApiMatchingAttachmentsAndSubjectEpisodesRequest
 */
export interface V1alpha1AttachmentReferenceApiMatchingAttachmentsAndSubjectEpisodesRequest {
	/**
	 * batch matching episodes and attachments request value object
	 * @type {BatchMatchingEpisodeAttachment}
	 * @memberof V1alpha1AttachmentReferenceApiMatchingAttachmentsAndSubjectEpisodes
	 */
	readonly batchMatchingEpisodeAttachment: BatchMatchingEpisodeAttachment;
}

/**
 * Request parameters for removeByTypeAndAttachmentIdAndReferenceId operation in V1alpha1AttachmentReferenceApi.
 * @export
 * @interface V1alpha1AttachmentReferenceApiRemoveByTypeAndAttachmentIdAndReferenceIdRequest
 */
export interface V1alpha1AttachmentReferenceApiRemoveByTypeAndAttachmentIdAndReferenceIdRequest {
	/**
	 *
	 * @type {AttachmentReference}
	 * @memberof V1alpha1AttachmentReferenceApiRemoveByTypeAndAttachmentIdAndReferenceId
	 */
	readonly attachmentReference?: AttachmentReference;
}

/**
 * Request parameters for saveAttachmentReference operation in V1alpha1AttachmentReferenceApi.
 * @export
 * @interface V1alpha1AttachmentReferenceApiSaveAttachmentReferenceRequest
 */
export interface V1alpha1AttachmentReferenceApiSaveAttachmentReferenceRequest {
	/**
	 *
	 * @type {AttachmentReference}
	 * @memberof V1alpha1AttachmentReferenceApiSaveAttachmentReference
	 */
	readonly attachmentReference?: AttachmentReference;
}

/**
 * V1alpha1AttachmentReferenceApi - object-oriented interface
 * @export
 * @class V1alpha1AttachmentReferenceApi
 * @extends {BaseAPI}
 */
export class V1alpha1AttachmentReferenceApi extends BaseAPI {
	/**
	 *
	 * @param {V1alpha1AttachmentReferenceApiDeleteAttachmentReferenceRequest} requestParameters Request parameters.
	 * @param {*} [options] Override http request option.
	 * @throws {RequiredError}
	 * @memberof V1alpha1AttachmentReferenceApi
	 */
	public deleteAttachmentReference(
		requestParameters: V1alpha1AttachmentReferenceApiDeleteAttachmentReferenceRequest,
		options?: AxiosRequestConfig
	) {
		return V1alpha1AttachmentReferenceApiFp(this.configuration)
			.deleteAttachmentReference(requestParameters.id, options)
			.then((request) => request(this.axios, this.basePath));
	}

	/**
	 *
	 * @param {V1alpha1AttachmentReferenceApiFindAllByTypeAndAttachmentIdRequest} requestParameters Request parameters.
	 * @param {*} [options] Override http request option.
	 * @throws {RequiredError}
	 * @memberof V1alpha1AttachmentReferenceApi
	 */
	public findAllByTypeAndAttachmentId(
		requestParameters: V1alpha1AttachmentReferenceApiFindAllByTypeAndAttachmentIdRequest,
		options?: AxiosRequestConfig
	) {
		return V1alpha1AttachmentReferenceApiFp(this.configuration)
			.findAllByTypeAndAttachmentId(
				requestParameters.type,
				requestParameters.attachmentId,
				options
			)
			.then((request) => request(this.axios, this.basePath));
	}

	/**
	 * Matching attachments to episodes for single subject.
	 * @param {V1alpha1AttachmentReferenceApiMatchingAttachmentsAndSubjectEpisodesRequest} requestParameters Request parameters.
	 * @param {*} [options] Override http request option.
	 * @throws {RequiredError}
	 * @memberof V1alpha1AttachmentReferenceApi
	 */
	public matchingAttachmentsAndSubjectEpisodes(
		requestParameters: V1alpha1AttachmentReferenceApiMatchingAttachmentsAndSubjectEpisodesRequest,
		options?: AxiosRequestConfig
	) {
		return V1alpha1AttachmentReferenceApiFp(this.configuration)
			.matchingAttachmentsAndSubjectEpisodes(
				requestParameters.batchMatchingEpisodeAttachment,
				options
			)
			.then((request) => request(this.axios, this.basePath));
	}

	/**
	 * Remove by type and attachmentId and referenceId
	 * @param {V1alpha1AttachmentReferenceApiRemoveByTypeAndAttachmentIdAndReferenceIdRequest} requestParameters Request parameters.
	 * @param {*} [options] Override http request option.
	 * @throws {RequiredError}
	 * @memberof V1alpha1AttachmentReferenceApi
	 */
	public removeByTypeAndAttachmentIdAndReferenceId(
		requestParameters: V1alpha1AttachmentReferenceApiRemoveByTypeAndAttachmentIdAndReferenceIdRequest = {},
		options?: AxiosRequestConfig
	) {
		return V1alpha1AttachmentReferenceApiFp(this.configuration)
			.removeByTypeAndAttachmentIdAndReferenceId(
				requestParameters.attachmentReference,
				options
			)
			.then((request) => request(this.axios, this.basePath));
	}

	/**
	 * Save attachment reference.
	 * @param {V1alpha1AttachmentReferenceApiSaveAttachmentReferenceRequest} requestParameters Request parameters.
	 * @param {*} [options] Override http request option.
	 * @throws {RequiredError}
	 * @memberof V1alpha1AttachmentReferenceApi
	 */
	public saveAttachmentReference(
		requestParameters: V1alpha1AttachmentReferenceApiSaveAttachmentReferenceRequest = {},
		options?: AxiosRequestConfig
	) {
		return V1alpha1AttachmentReferenceApiFp(this.configuration)
			.saveAttachmentReference(requestParameters.attachmentReference, options)
			.then((request) => request(this.axios, this.basePath));
	}
}
