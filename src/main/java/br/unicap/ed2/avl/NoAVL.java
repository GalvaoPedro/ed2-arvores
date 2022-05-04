package br.unicap.ed2.avl;

public class NoAVL {
  
	private NoAVL left;
	private NoAVL rigth;
	private NoAVL dad;
	private int key;
	private int balanceamento;

	public NoAVL(int k) {
		setLeft(setRight(setDad(null)));
		setBalanceamento(0);
		setKey(k);
	}
	

	public String toString() {
		return Integer.toString(getKey());
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getBalanceamento() {
		return balanceamento;
	}

	public void setBalanceamento(int balanceamento) {
		this.balanceamento = balanceamento;
	}

	public NoAVL getDad() {
		return dad;
	}

	public NoAVL setDad(NoAVL dad) {
		this.dad = dad;
		return dad;
	}

	public NoAVL getRight() {
		return rigth;
	}

	public NoAVL setRight(NoAVL rigth) {
		this.rigth = rigth;
		return rigth;
	}

	public NoAVL getLeft() {
		return left;
	}

	public void setLeft(NoAVL left) {
		this.left = left;
	}
}
