
// this file is generated — do not edit it


/// <reference types="@sveltejs/kit" />

/**
 * Environment variables [loaded by Vite](https://vitejs.dev/guide/env-and-mode.html#env-files) from `.env` files and `process.env`. Like [`$env/dynamic/private`](https://svelte.dev/docs/kit/$env-dynamic-private), this module cannot be imported into client-side code. This module only includes variables that _do not_ begin with [`config.kit.env.publicPrefix`](https://svelte.dev/docs/kit/configuration#env) _and do_ start with [`config.kit.env.privatePrefix`](https://svelte.dev/docs/kit/configuration#env) (if configured).
 * 
 * _Unlike_ [`$env/dynamic/private`](https://svelte.dev/docs/kit/$env-dynamic-private), the values exported from this module are statically injected into your bundle at build time, enabling optimisations like dead code elimination.
 * 
 * ```ts
 * import { API_KEY } from '$env/static/private';
 * ```
 * 
 * Note that all environment variables referenced in your code should be declared (for example in an `.env` file), even if they don't have a value until the app is deployed:
 * 
 * ```
 * MY_FEATURE_FLAG=""
 * ```
 * 
 * You can override `.env` values from the command line like so:
 * 
 * ```bash
 * MY_FEATURE_FLAG="enabled" npm run dev
 * ```
 */
declare module '$env/static/private' {
	export const XDG_SECRETS_DIR: string;
	export const SYSTEMD_SLEEP_FREEZE_USER_SESSIONS: string;
	export const MAIL: string;
	export const _tide_color_separator_same_color: string;
	export const _tide_pad: string;
	export const FORCE_COLOR: string;
	export const _JAVA_AWT_WM_NONREPARENTING: string;
	export const PATH: string;
	export const MOZ_ENABLE_WAYLAND: string;
	export const LOGNAME: string;
	export const XDG_BACKEND: string;
	export const WAYLAND_DISPLAY: string;
	export const XDG_SESSION_ID: string;
	export const _tide_location_color: string;
	export const EDITOR: string;
	export const npm_config_color: string;
	export const XDG_SEAT: string;
	export const HYPRLAND_CMD: string;
	export const XDG_VTNR: string;
	export const DBUS_SESSION_BUS_ADDRESS: string;
	export const INVOCATION_ID: string;
	export const SHLVL: string;
	export const XDG_DATA_DIRS: string;
	export const SHELL: string;
	export const DEBUG_COLORS: string;
	export const XDG_SESSION_CLASS: string;
	export const COLORTERM: string;
	export const MOZ_GMP_PATH: string;
	export const DISPLAY: string;
	export const HOME: string;
	export const MEMORY_PRESSURE_WATCH: string;
	export const XDG_CURRENT_DESKTOP: string;
	export const DEBUGINFOD_URLS: string;
	export const TERM: string;
	export const MOCHA_COLORS: string;
	export const QT_QPA_PLATFORMTHEME: string;
	export const LANG: string;
	export const CREDENTIALS_DIRECTORY: string;
	export const DEBUGINFOD_IMA_CERT_PATH: string;
	export const GNOME_KEYRING_CONTROL: string;
	export const MEMORY_PRESSURE_WRITE: string;
	export const XCURSOR_SIZE: string;
	export const SYSTEMD_EXEC_PID: string;
	export const SSH_AUTH_SOCK: string;
	export const XDG_RUNTIME_DIR: string;
	export const HYPRLAND_INSTANCE_SIGNATURE: string;
	export const GPG_AGENT_INFO: string;
	export const HL_INITIAL_WORKSPACE_TOKEN: string;
	export const USER: string;
	export const XDG_SESSION_TYPE: string;
	export const PWD: string;
	export const _: string;
	export const NODE_ENV: string;
}

/**
 * Similar to [`$env/static/private`](https://svelte.dev/docs/kit/$env-static-private), except that it only includes environment variables that begin with [`config.kit.env.publicPrefix`](https://svelte.dev/docs/kit/configuration#env) (which defaults to `PUBLIC_`), and can therefore safely be exposed to client-side code.
 * 
 * Values are replaced statically at build time.
 * 
 * ```ts
 * import { PUBLIC_BASE_URL } from '$env/static/public';
 * ```
 */
declare module '$env/static/public' {
	
}

/**
 * This module provides access to runtime environment variables, as defined by the platform you're running on. For example if you're using [`adapter-node`](https://github.com/sveltejs/kit/tree/main/packages/adapter-node) (or running [`vite preview`](https://svelte.dev/docs/kit/cli)), this is equivalent to `process.env`. This module only includes variables that _do not_ begin with [`config.kit.env.publicPrefix`](https://svelte.dev/docs/kit/configuration#env) _and do_ start with [`config.kit.env.privatePrefix`](https://svelte.dev/docs/kit/configuration#env) (if configured).
 * 
 * This module cannot be imported into client-side code.
 * 
 * Dynamic environment variables cannot be used during prerendering.
 * 
 * ```ts
 * import { env } from '$env/dynamic/private';
 * console.log(env.DEPLOYMENT_SPECIFIC_VARIABLE);
 * ```
 * 
 * > In `dev`, `$env/dynamic` always includes environment variables from `.env`. In `prod`, this behavior will depend on your adapter.
 */
declare module '$env/dynamic/private' {
	export const env: {
		XDG_SECRETS_DIR: string;
		SYSTEMD_SLEEP_FREEZE_USER_SESSIONS: string;
		MAIL: string;
		_tide_color_separator_same_color: string;
		_tide_pad: string;
		FORCE_COLOR: string;
		_JAVA_AWT_WM_NONREPARENTING: string;
		PATH: string;
		MOZ_ENABLE_WAYLAND: string;
		LOGNAME: string;
		XDG_BACKEND: string;
		WAYLAND_DISPLAY: string;
		XDG_SESSION_ID: string;
		_tide_location_color: string;
		EDITOR: string;
		npm_config_color: string;
		XDG_SEAT: string;
		HYPRLAND_CMD: string;
		XDG_VTNR: string;
		DBUS_SESSION_BUS_ADDRESS: string;
		INVOCATION_ID: string;
		SHLVL: string;
		XDG_DATA_DIRS: string;
		SHELL: string;
		DEBUG_COLORS: string;
		XDG_SESSION_CLASS: string;
		COLORTERM: string;
		MOZ_GMP_PATH: string;
		DISPLAY: string;
		HOME: string;
		MEMORY_PRESSURE_WATCH: string;
		XDG_CURRENT_DESKTOP: string;
		DEBUGINFOD_URLS: string;
		TERM: string;
		MOCHA_COLORS: string;
		QT_QPA_PLATFORMTHEME: string;
		LANG: string;
		CREDENTIALS_DIRECTORY: string;
		DEBUGINFOD_IMA_CERT_PATH: string;
		GNOME_KEYRING_CONTROL: string;
		MEMORY_PRESSURE_WRITE: string;
		XCURSOR_SIZE: string;
		SYSTEMD_EXEC_PID: string;
		SSH_AUTH_SOCK: string;
		XDG_RUNTIME_DIR: string;
		HYPRLAND_INSTANCE_SIGNATURE: string;
		GPG_AGENT_INFO: string;
		HL_INITIAL_WORKSPACE_TOKEN: string;
		USER: string;
		XDG_SESSION_TYPE: string;
		PWD: string;
		_: string;
		NODE_ENV: string;
		[key: `PUBLIC_${string}`]: undefined;
		[key: `${string}`]: string | undefined;
	}
}

/**
 * Similar to [`$env/dynamic/private`](https://svelte.dev/docs/kit/$env-dynamic-private), but only includes variables that begin with [`config.kit.env.publicPrefix`](https://svelte.dev/docs/kit/configuration#env) (which defaults to `PUBLIC_`), and can therefore safely be exposed to client-side code.
 * 
 * Note that public dynamic environment variables must all be sent from the server to the client, causing larger network requests — when possible, use `$env/static/public` instead.
 * 
 * Dynamic environment variables cannot be used during prerendering.
 * 
 * ```ts
 * import { env } from '$env/dynamic/public';
 * console.log(env.PUBLIC_DEPLOYMENT_SPECIFIC_VARIABLE);
 * ```
 */
declare module '$env/dynamic/public' {
	export const env: {
		[key: `PUBLIC_${string}`]: string | undefined;
	}
}
