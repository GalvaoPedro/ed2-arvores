package br.unicap.ed2.avl;

public class ArvoreAVL {

	public NoAVL root;

	public boolean Root(int valor) {
		if (root == null) {
			return false;
		}
		return root.getKey() == valor;
	}

	public void inserir(int noAVL) {
		NoAVL tree = new NoAVL(noAVL);
		inserirNoAVL(root, tree);
	}

	public void inserirNoAVL(NoAVL no, NoAVL valor) {
		if (no == null) {
			this.root = valor;
		} else {
			if (valor.getKey() < no.getKey()) {// valor < raiz inserir na esquerda
				if (no.getLeft() == null) {
					no.setLeft(valor);
					valor.setDad(no);
					balancear(no);
					//System.out.println("inseriu a esquerda do " + no );
				} else {
					inserirNoAVL(no.getLeft(), valor);
					//System.out.println("inseriu a esquerda do " + no );
				}
			} else if (valor.getKey() > no.getKey()) {// valor > raiz inserir na direita
				if (no.getRight() == null) {
					no.setRight(valor);
					valor.setDad(no);
					balancear(no);
					//System.out.println("inseriu a direita do " + no );
				} else {
					inserirNoAVL(no.getRight(), valor);
					//System.out.println("inseriu a direita do " + no );
				}
			} else {
				System.out.println("Nó já existente!");
			}
		}
	}

	public void balancear(NoAVL no) {
		no.setBalanceamento(alturaNo(no.getRight()) - alturaNo(no.getLeft()));
		int balanceamento = no.getBalanceamento();

		if (balanceamento == -2) {
			if (alturaNo(no.getLeft().getLeft()) >= alturaNo(no.getLeft().getRight())) {
				no = rotacaoDireita(no);
				//System.out.println("Rotação a direita do " + no);
			} else {
				no = rotacaoLR(no);// LR
				//System.out.println("Rotação  DUPLA LR do " + no);
			}
		} else if (balanceamento == 2) {
			if (alturaNo(no.getRight().getRight()) >= alturaNo(no.getRight().getLeft())) {
				no = rotacaoEsquerda(no);
				//System.out.println("Rotação a esquerda do " + no);
			} else {
				no = rotacaoRL(no);// RL
				//System.out.println("Rotação  DUPLA RL do " + no);
			}
		}
		if (no.getDad() != null) {
			balancear(no.getDad());
		} else {
			this.root = no;
		}
	}

	public NoAVL procurar(int key) {
		return procurarComBusca(root, key);
	}

	public NoAVL procurarComBusca(NoAVL no, int valor) {
		while (no != null) {

			if (valor == no.getKey()) {// valor procurado == nó
				return no;
			}

			else if (valor < no.getKey()) {// valor procurado < nó, procurar na subarvore da esquerda do nó
				no = no.getLeft();
			}

			else {// caso o valor procurado > nó, procurar na subarvore da direita do nó
				no = no.getRight();
			}
		}
		// caso chave não for encontrada
		return null;
	}

	public void deletar(int valor) {
		deletarAVL(root, valor);
	}

	public void deletarAVL(NoAVL no, int valor) {
		if (no != null) {
			if (no.getKey() > valor) {// se valor for menor que referencia do nó, deleta filho da esqueda
				deletarAVL(no.getLeft(), valor);// procura seu sucessor no menor da subarvore da direita
			} else if (no.getKey() < valor) {// se valor for maior que referencia do nó, deleta filho da direita
				deletarAVL(no.getRight(), valor);
			} else if (no.getKey() == valor) {// se valor for igual referencia do nó, deleta ele
				deletarBusca(no);
			}
		} else {
			return;
		}
	}

	public void deletarBusca(NoAVL remove) {
		NoAVL no, aux;
		// caso exista apenas 1 nó
		if (remove.getLeft() == null || remove.getRight() == null) {
			if (remove.getDad() == null) {
				root = null;
				remove = null;
				return;
			}
			no = remove;
		} else {// caso exista mais de 1 nó
			no = sucessor(remove);
			remove.setKey(no.getKey());
		}
		if (no.getLeft() != null) {
			aux = no.getLeft();
		} else {
			aux = no.getRight();
		}
		if (aux != null
				) {
			aux.setDad(no.getDad());
		}

		if (no.getDad() == null) {
			root = aux;
		} else {
			if (no == no.getDad().getLeft()) {
				no.getDad().setLeft(aux);
			} else {
				no.getDad().setRight(aux);
			}
			balancear(no.getDad());
		}
	}

	public int alturaNo(NoAVL no) {
		if (no == null) {
			return -1;
		}
		if (no.getLeft() == null && no.getRight() == null) {
			return 0;
		} else if (no.getRight() == null) {
			return 1 + alturaNo(no.getLeft());
		} else if (no.getLeft() == null) {
			return 1 + alturaNo(no.getRight());
		} else {
			return 1 + Math.max(alturaNo(no.getLeft()), alturaNo(no.getRight()));
		}

	}

	// achar o sucessor do nó excluido
	public NoAVL sucessor(NoAVL valor) {
		// procura o menor da direita para substituir a raiz
		if (valor.getRight() != null) {
			NoAVL valor1 = valor.getRight();
			while (valor1.getLeft() != null) {
				valor1 = valor1.getLeft();
			}
			return valor1;
		} else {
			NoAVL valor2 = valor.getDad();
			while (valor2 != null && valor == valor2.getLeft()) {
				valor = valor2;
				valor2 = valor.getDad();
			}
			return valor2;
		}
	}

	public NoAVL rotacaoEsquerda(NoAVL no) {
		NoAVL dir = no.getRight();
		dir.setDad(no.getDad());
		no.setRight(dir.getLeft());
		if (no.getRight() != null) {
			no.getRight().setDad(no);
		}
		dir.setLeft(no);
		no.setDad(dir);
		if (dir.getDad() != null) {
			if (dir.getDad().getRight() == no) {
				dir.getDad().setRight(dir);
			} else if (dir.getDad().getLeft() == no) {
				dir.getDad().setLeft(dir);
			}
		}
		setBalanceamento(no);
		setBalanceamento(dir);

		return dir;
	}

	public NoAVL rotacaoDireita(NoAVL no) {
		NoAVL esq = no.getLeft();
		esq.setDad(no.getDad());
		no.setLeft(esq.getRight());
		if (no.getLeft() != null) {
			no.getLeft().setDad(no);
		}
		esq.setRight(no);
		no.setDad(esq);
		if (esq.getDad() != null) {
			if (esq.getDad().getRight() == no) {
				esq.getDad().setRight(esq);
			} else if (esq.getDad().getLeft() == no) {
				esq.getDad().setLeft(esq);
			}
		}
		setBalanceamento(no);
		setBalanceamento(esq);

		return esq;
	}

	public NoAVL rotacaoLR(NoAVL no) {
		no.setLeft(rotacaoEsquerda(no.getLeft()));
		return rotacaoDireita(no);
	}

	public NoAVL rotacaoRL(NoAVL no) {
		no.setRight(rotacaoDireita(no.getRight()));
		return rotacaoEsquerda(no);
	}

	private void setBalanceamento(NoAVL no) {
		no.setBalanceamento(alturaNo(no.getRight()) - alturaNo(no.getLeft()));
	}

}