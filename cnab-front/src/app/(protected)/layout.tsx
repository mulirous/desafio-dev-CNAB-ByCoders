import type { Metadata } from "next";
import "@/globals.css"; // Importa os estilos globais

export const metadata: Metadata = {
	title: "Meu App",
	description: "Aplicação Next.js com RootLayout",
};

export default function ProtectedLayout({ children }: { children: React.ReactNode }) {
	return (
		<html
			lang="pt-BR"
			className="w-full h-full"
		>
			<body className="w-full min-h-screen flex flex-col">
				{children} {/* Todas as páginas serão renderizadas aqui */}
			</body>
		</html>
	);
}
