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

import type { Configuration } from "../configuration";
import type { AxiosPromise, AxiosInstance, AxiosRequestConfig } from "axios";
import globalAxios from "axios";
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
} from "../common";
// @ts-ignore
import {
  BASE_PATH,
  COLLECTION_FORMATS,
  RequestArgs,
  BaseAPI,
  RequiredError,
} from "../base";
// @ts-ignore
import { FileRelations } from "../models";
// @ts-ignore
import { Subtitle } from "../models";
/**
 * V1alpha1FileRelationApi - axios parameter creator
 * @export
 */
export const V1alpha1FileRelationApiAxiosParamCreator = function (
  configuration?: Configuration
) {
  return {
    /**
     *
     * @param {number} fileId File ID
     * @param {'VIDEO_SUBTITLE'} relationType Relation type
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    findFileRelations: async (
      fileId: number,
      relationType: "VIDEO_SUBTITLE",
      options: AxiosRequestConfig = {}
    ): Promise<RequestArgs> => {
      // verify required parameter 'fileId' is not null or undefined
      assertParamExists("findFileRelations", "fileId", fileId);
      // verify required parameter 'relationType' is not null or undefined
      assertParamExists("findFileRelations", "relationType", relationType);
      const localVarPath = `/api/v1alpha1/file/relations`;
      // use dummy base URL string because the URL constructor only accepts absolute URLs.
      const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
      let baseOptions;
      if (configuration) {
        baseOptions = configuration.baseOptions;
      }

      const localVarRequestOptions = {
        method: "GET",
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

      if (fileId !== undefined) {
        localVarQueryParameter["fileId"] = fileId;
      }

      if (relationType !== undefined) {
        localVarQueryParameter["relationType"] = relationType;
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
     * @param {number} fileId Video File ID
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    findVideoSubtitles: async (
      fileId: number,
      options: AxiosRequestConfig = {}
    ): Promise<RequestArgs> => {
      // verify required parameter 'fileId' is not null or undefined
      assertParamExists("findVideoSubtitles", "fileId", fileId);
      const localVarPath =
        `/api/v1alpha1/file/relation/video/subtitle/{fileId}`.replace(
          `{${"fileId"}}`,
          encodeURIComponent(String(fileId))
        );
      // use dummy base URL string because the URL constructor only accepts absolute URLs.
      const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
      let baseOptions;
      if (configuration) {
        baseOptions = configuration.baseOptions;
      }

      const localVarRequestOptions = {
        method: "GET",
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
 * V1alpha1FileRelationApi - functional programming interface
 * @export
 */
export const V1alpha1FileRelationApiFp = function (
  configuration?: Configuration
) {
  const localVarAxiosParamCreator =
    V1alpha1FileRelationApiAxiosParamCreator(configuration);
  return {
    /**
     *
     * @param {number} fileId File ID
     * @param {'VIDEO_SUBTITLE'} relationType Relation type
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    async findFileRelations(
      fileId: number,
      relationType: "VIDEO_SUBTITLE",
      options?: AxiosRequestConfig
    ): Promise<
      (axios?: AxiosInstance, basePath?: string) => AxiosPromise<FileRelations>
    > {
      const localVarAxiosArgs =
        await localVarAxiosParamCreator.findFileRelations(
          fileId,
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
    /**
     *
     * @param {number} fileId Video File ID
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    async findVideoSubtitles(
      fileId: number,
      options?: AxiosRequestConfig
    ): Promise<
      (
        axios?: AxiosInstance,
        basePath?: string
      ) => AxiosPromise<Array<Subtitle>>
    > {
      const localVarAxiosArgs =
        await localVarAxiosParamCreator.findVideoSubtitles(fileId, options);
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
 * V1alpha1FileRelationApi - factory interface
 * @export
 */
export const V1alpha1FileRelationApiFactory = function (
  configuration?: Configuration,
  basePath?: string,
  axios?: AxiosInstance
) {
  const localVarFp = V1alpha1FileRelationApiFp(configuration);
  return {
    /**
     *
     * @param {V1alpha1FileRelationApiFindFileRelationsRequest} requestParameters Request parameters.
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    findFileRelations(
      requestParameters: V1alpha1FileRelationApiFindFileRelationsRequest,
      options?: AxiosRequestConfig
    ): AxiosPromise<FileRelations> {
      return localVarFp
        .findFileRelations(
          requestParameters.fileId,
          requestParameters.relationType,
          options
        )
        .then((request) => request(axios, basePath));
    },
    /**
     *
     * @param {V1alpha1FileRelationApiFindVideoSubtitlesRequest} requestParameters Request parameters.
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    findVideoSubtitles(
      requestParameters: V1alpha1FileRelationApiFindVideoSubtitlesRequest,
      options?: AxiosRequestConfig
    ): AxiosPromise<Array<Subtitle>> {
      return localVarFp
        .findVideoSubtitles(requestParameters.fileId, options)
        .then((request) => request(axios, basePath));
    },
  };
};

/**
 * Request parameters for findFileRelations operation in V1alpha1FileRelationApi.
 * @export
 * @interface V1alpha1FileRelationApiFindFileRelationsRequest
 */
export interface V1alpha1FileRelationApiFindFileRelationsRequest {
  /**
   * File ID
   * @type {number}
   * @memberof V1alpha1FileRelationApiFindFileRelations
   */
  readonly fileId: number;

  /**
   * Relation type
   * @type {'VIDEO_SUBTITLE'}
   * @memberof V1alpha1FileRelationApiFindFileRelations
   */
  readonly relationType: "VIDEO_SUBTITLE";
}

/**
 * Request parameters for findVideoSubtitles operation in V1alpha1FileRelationApi.
 * @export
 * @interface V1alpha1FileRelationApiFindVideoSubtitlesRequest
 */
export interface V1alpha1FileRelationApiFindVideoSubtitlesRequest {
  /**
   * Video File ID
   * @type {number}
   * @memberof V1alpha1FileRelationApiFindVideoSubtitles
   */
  readonly fileId: number;
}

/**
 * V1alpha1FileRelationApi - object-oriented interface
 * @export
 * @class V1alpha1FileRelationApi
 * @extends {BaseAPI}
 */
export class V1alpha1FileRelationApi extends BaseAPI {
  /**
   *
   * @param {V1alpha1FileRelationApiFindFileRelationsRequest} requestParameters Request parameters.
   * @param {*} [options] Override http request option.
   * @throws {RequiredError}
   * @memberof V1alpha1FileRelationApi
   */
  public findFileRelations(
    requestParameters: V1alpha1FileRelationApiFindFileRelationsRequest,
    options?: AxiosRequestConfig
  ) {
    return V1alpha1FileRelationApiFp(this.configuration)
      .findFileRelations(
        requestParameters.fileId,
        requestParameters.relationType,
        options
      )
      .then((request) => request(this.axios, this.basePath));
  }

  /**
   *
   * @param {V1alpha1FileRelationApiFindVideoSubtitlesRequest} requestParameters Request parameters.
   * @param {*} [options] Override http request option.
   * @throws {RequiredError}
   * @memberof V1alpha1FileRelationApi
   */
  public findVideoSubtitles(
    requestParameters: V1alpha1FileRelationApiFindVideoSubtitlesRequest,
    options?: AxiosRequestConfig
  ) {
    return V1alpha1FileRelationApiFp(this.configuration)
      .findVideoSubtitles(requestParameters.fileId, options)
      .then((request) => request(this.axios, this.basePath));
  }
}
