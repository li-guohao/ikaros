import axios from 'axios';
import { i18n } from '../locales';
import type { AxiosError, AxiosInstance } from 'axios';
import {
	PluginIkarosRunV1alpha1PluginApi,
	SettingIkarosRunV1alpha1ConfigMapApi,
	V1alpha1EpisodeFileApi,
	V1alpha1FileApi,
	V1alpha1IndicesApi,
	V1alpha1PluginApi,
	V1alpha1SubjectApi,
	V1alpha1SubjectSyncPlatformApi,
	V1alpha1UserApi,
} from '@runikaros/api-client';
import { ElMessage } from 'element-plus';

const baseURL = import.meta.env.VITE_API_URL;

const axiosInstance = axios.create({
	baseURL,
	withCredentials: true,
});

export interface ProblemDetail {
	detail: string;
	instance: string;
	status: number;
	title: string;
	type?: string;
}

axiosInstance.interceptors.response.use(
	(response) => response,
	async (error: AxiosError<ProblemDetail>) => {
		// @ts-ignore
		let msg = error?.response?.data?.message;
		if (!msg) {
			msg = error.message;
		}

		if (/Network Error/.test(msg)) {
			console.error(
				i18n.global.t('core.common.exception.network_error') + ': ' + msg,
				error
			);
			ElMessage.error(
				i18n.global.t('core.common.exception.network_error') + ': ' + msg
			);
			return Promise.reject(error);
		}

		const errorResponse = error.response;

		if (!errorResponse) {
			console.error(
				i18n.global.t('core.common.exception.network_error') + ': ' + msg,
				error
			);
			ElMessage.error(
				i18n.global.t('core.common.exception.network_error') + ': ' + msg
			);
			return Promise.reject(error);
		}

		const { status } = errorResponse;

		const { title } = errorResponse.data;

		if (status === 400) {
			console.error(
				i18n.global.t('core.common.exception.request_parameter_error', {
					title,
				}),
				error
			);
			ElMessage.error(
				i18n.global.t('core.common.exception.request_parameter_error') +
					': ' +
					msg
			);
		} else if (status === 401) {
			console.error(
				i18n.global.t('core.common.exception.unauthorized') + ': ' + msg,
				error
			);
			ElMessage.error(
				i18n.global.t('core.common.exception.unauthorized') + ': ' + msg
			);
		} else if (status === 403) {
			console.error(
				i18n.global.t('core.common.exception.forbidden') + ': ' + msg,
				error
			);
			ElMessage.error(
				i18n.global.t('core.common.exception.forbidden') + ': ' + msg
			);
		} else if (status === 404) {
			console.error(
				i18n.global.t('core.common.exception.not_found') + ': ' + msg,
				error
			);
			ElMessage.error(
				i18n.global.t('core.common.exception.not_found') + ': ' + msg
			);
		} else if (status === 500) {
			console.error(
				i18n.global.t(
					'core.common.exception.server_internal_error_with_title'
				) +
					': ' +
					msg,
				error
			);
			ElMessage.error(
				i18n.global.t(
					'core.common.exception.server_internal_error_with_title'
				) +
					': ' +
					msg
			);
		} else {
			console.error(
				i18n.global.t('core.common.exception.unknown_error_with_title', {
					title,
				}) +
					': ' +
					msg,
				error
			);
			ElMessage.error(
				i18n.global.t('core.common.exception.unknown_error_with_title') +
					': ' +
					msg
			);
		}

		return Promise.reject(error);
	}
);

axiosInstance.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

const apiClient = setupApiClient(axiosInstance);

// eslint-disable-next-line no-shadow, no-unused-vars
function setupApiClient(axios: AxiosInstance) {
	return {
		// core endpoints
		user: new V1alpha1UserApi(undefined, baseURL, axios),
		corePlugin: new V1alpha1PluginApi(undefined, baseURL, axios),
		file: new V1alpha1FileApi(undefined, baseURL, axios),
		subject: new V1alpha1SubjectApi(undefined, baseURL, axios),
		subjectSyncPlatform: new V1alpha1SubjectSyncPlatformApi(
			undefined,
			baseURL,
			axios
		),
		episodefile: new V1alpha1EpisodeFileApi(undefined, baseURL, axios),
		indices: new V1alpha1IndicesApi(undefined, baseURL, axios),
		// custom endpoints
		plugin: new PluginIkarosRunV1alpha1PluginApi(undefined, baseURL, axios),
		configmap: new SettingIkarosRunV1alpha1ConfigMapApi(
			undefined,
			baseURL,
			axios
		),
	};
}

export { apiClient };