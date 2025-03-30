"use client";
import { z } from "zod";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "./ui/card";
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "./ui/form";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { useRouter } from "next/navigation"; // Use useRouter em vez de redirect
import { Input } from "./ui/input";
import { Button } from "./ui/button";
import { useState } from "react";
import login from "../service/login";

const loginSchema = z.object({
	email: z.string().email("Email inválido"),
	password: z.string().min(1, "A senha é obrigatória"),
});

type LoginFormValues = z.infer<typeof loginSchema>;

export default function LoginForm() {
	const router = useRouter();
	const [isLoading, setIsLoading] = useState(false);
	const [error, setError] = useState("");

	const form = useForm<LoginFormValues>({
		resolver: zodResolver(loginSchema),
		defaultValues: {
			email: "",
			password: "",
		},
		mode: "onSubmit",
	});

	const onSubmit = async (data: LoginFormValues) => {
		setIsLoading(true);
		const loginFormData = {
			email: data.email,
			password: data.password,
		};
		setError("");

		try {
			await login(loginFormData);
			router.push("/home"); // Use router.push em vez de redirect
		} catch (err) {
			setError("Falha no login. Verifique suas credenciais.");
			setTimeout(() => setError(""), 3000);
			console.error(err);
		} finally {
			setIsLoading(false);
		}
	};

	return (
		<Card className="border-none w-[400px] h-[500px] mx-auto mt-10 p-4 text-blue-500 font-bold">
			<CardHeader className="flex gap-2 text-center items-center p-4">
				<CardTitle className="font-extrabold text-4xl">Entrar</CardTitle>
				<CardDescription className="font-bold text-xs w-full pt-5">
					Gerencie seu CNAB aqui!
				</CardDescription>
			</CardHeader>
			<CardContent>
				<Form {...form}>
					<form
						onSubmit={form.handleSubmit(onSubmit)}
						className="space-y-10"
					>
						<FormField
							control={form.control}
							name="email"
							render={({ field }) => (
								<FormItem>
									<FormLabel>Email</FormLabel>
									<FormControl>
										<Input
											placeholder="Seu Email"
											type="email"
											disabled={isLoading}
											{...field}
										/>
									</FormControl>
									<FormMessage />
								</FormItem>
							)}
						/>
						<FormField
							control={form.control}
							name="password"
							render={({ field }) => (
								<FormItem>
									<FormLabel>Senha</FormLabel>
									<FormControl>
										<Input
											placeholder="Sua Senha"
											type="password"
											disabled={isLoading}
											{...field}
										/>
									</FormControl>
									<FormMessage />
								</FormItem>
							)}
						/>
						<Button
							type="submit"
							className="w-full bg-blue-400 text-white font-bold hover:bg-blue-500/90 cursor-pointer"
							variant="secondary"
							disabled={isLoading}
						>
							{isLoading ? "Entrando..." : "Entrar"}
						</Button>
					</form>
				</Form>
				{error && <div className="text-red-500 mb-4 text-center pt-4">{error}</div>}
			</CardContent>
		</Card>
	);
}
