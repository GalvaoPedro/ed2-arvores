package br.unicap.ed2.avl;

import java.util.Scanner;

import br.unicap.ed2.base.BinaryTreePrinter;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArvoreAVL no = new ArvoreAVL();
		BinaryTreePrinter p;
		int op, num;
		
		no.inserir(7);
		no.inserir(3);
		no.inserir(18);
		no.inserir(10);
		no.inserir(22);
		no.inserir(8);
		no.inserir(11);
		no.inserir(26);
		no.inserir(2);
		no.inserir(6);
		no.inserir(13);
		p = new BinaryTreePrinter(no);
		System.out.println("--- INSERÇÃO ---");
		p.imprimir(System.out);
		System.out.println("");

		no.deletar(18);
		no.deletar(11);
		no.deletar(3);
		no.deletar(10);
		no.deletar(22);

		System.out.println("\n--- REMOÇÃO ---");
		p.imprimir(System.out);
		
		
		/*
		// inserir um por um
		do {
			exibeMenu();
			op = in.nextInt();
			switch (op) {
			case 1:
				System.out.println("Digite um numero para inserir!");
				num = in.nextInt();
				no.inserir(num);
				p = new BinaryTreePrinter(no);
				System.out.println("--- INSERÇÃO ---");
				p.imprimir(System.out);
				System.out.println("");
				break;

			case 2:
				System.out.println("Digite um numero para Deletar!");
				num = in.nextInt();
				no.deletar(num);
				p = new BinaryTreePrinter(no);
				System.out.println("--- REMOÇÃO ---");
				p.imprimir(System.out);
				System.out.println("");
				break;
			default:
				System.out.println("Opção inválida!");
			}

		} while (op != 3);
		*/
	}

	/*public static void exibeMenu() {
		System.out.println("1 - Inserir um nó");
		System.out.println("2 - Remover um nó");
		System.out.println("3 - Sair");
		System.out.println("Escolha uma opção!");
	}*/
	
}
