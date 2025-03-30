import type { Config } from "tailwindcss";

const config: Config = {
	content: ["./src/**/*.{js,ts,jsx,tsx,mdx}"], // Verifique se está apontando para sua pasta src
	theme: {
		extend: {},
	},
	plugins: [],
};

export default config;
