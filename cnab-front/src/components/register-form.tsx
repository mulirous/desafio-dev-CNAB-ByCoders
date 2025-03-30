"use client";
import { useForm } from "react-hook-form";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "./ui/card";
import {
	Form,
	FormField,
	FormControl,
	FormItem,
	FormLabel,
	FormDescription,
	FormMessage,
} from "./ui/form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { Input } from "./ui/input";
import { Button } from "./ui/button"; // Importando o botão do shadcn/ui
import { useRouter } from "next/navigation";
import register from "@/service/register";
import { useState } from "react";
const registerSchema = z.object({
	nome: z.string().min(4, "O seu nome deve conter ao menos 4 caracteres"),
	email: z.string().email("Email inválido"),
	password: z.string().min(8, "Sua senha deve conter ao menos 8 caracteres"),
});

type RegisterFormValues = z.infer<typeof registerSchema>;

export const RegisterForm = () => {
	const router = useRouter();
	const [isLoading, setIsLoading] = useState(false);
	const [error, setError] = useState("");

	const form = useForm<RegisterFormValues>({
		resolver: zodResolver(registerSchema),
		defaultValues: {
			nome: "",
			email: "",
			password: "",
		},
		mode: "onSubmit",
	});

	const onSubmit = async (data: RegisterFormValues) => {
		console.log("Dados enviados: ", data);
		setError("");

		const registerFormData = {
			name: data.nome,
			email: data.email,
			password: data.password,
		};

		try {
			await register(registerFormData);
			router.push("/"); // Use router.push em vez de redirect
		} catch (err) {
			setError("Falha no cadastro. Você já não tem uma conta?.");
			setTimeout(() => setError(""), 3000);
			console.error(err);
		} finally {
			setIsLoading(false);
		}
	};

	return (
		<Card className="border-none w-[600px] h-[700px] mx-auto mt-10 p-4 text-blue-500 font-bold">
			<CardHeader className="flex gap-2 text-center items-center p-4">
				<CardTitle className="font-extrabold text-4xl">Cadastre-se!</CardTitle>
				<CardDescription className="font-bold text-xs pt-5">
					Tenha a oportunidade de registrar seu CNAB
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
							name="nome"
							render={({ field }) => (
								<FormItem>
									<FormLabel>Nome</FormLabel>
									<FormControl>
										<Input
											placeholder="Seu Nome"
											{...field}
										/>
									</FormControl>
									<FormMessage className="text-rose-700" />
								</FormItem>
							)}
						/>

						<FormField
							control={form.control}
							name="email"
							render={({ field }) => (
								<FormItem>
									<FormLabel>Email</FormLabel>
									<FormControl>
										<Input
											type="email"
											placeholder="Seu Email"
											{...field}
										/>
									</FormControl>
									<FormMessage className="text-rose-700" />
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
											type="password"
											placeholder="Sua Senha"
											{...field}
										/>
									</FormControl>
									<FormMessage className="text-rose-700" />
								</FormItem>
							)}
						/>

						<Button
							type="submit"
							className="w-full bg-blue-400 text-white font-bold hover:bg-blue-500/90 cursor-pointer"
							variant="secondary"
						>
							Cadastrar
						</Button>
					</form>
				</Form>
				{error && <div className="text-red-500 mb-4 text-center pt-4">{error}</div>}
			</CardContent>
		</Card>
	);
};
