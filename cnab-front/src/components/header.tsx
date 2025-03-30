import { Button } from "./ui/button";
import Link from "next/link";

export const Header = () => {
	return (
		<header className="w-full h-20 flex items-center justify-between px-8 bg-blue-500 shadow-md">
			<h3 className="font-bold text-2xl text-white">CNAB Online</h3>
			<div className="flex items-center gap-4">
				<Button
					variant="ghost"
					className="text-white hover:bg-blue-600"
				>
					<Link
						href="/auth/sign-in"
						className="w-full h-full"
					>
						Login
					</Link>
				</Button>
				<Button
					variant="ghost"
					className="text-white hover:bg-blue-600"
				>
					<Link
						href="/auth/sign-up"
						className="w-full h-full"
					>
						Cadastre-se
					</Link>
				</Button>
			</div>
		</header>
	);
};
