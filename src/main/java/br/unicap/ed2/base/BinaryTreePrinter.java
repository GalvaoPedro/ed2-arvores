package br.unicap.ed2.base;
import java.io.PrintStream;

import br.unicap.ed2.avl.ArvoreAVL;
import br.unicap.ed2.avl.NoAVL;

//Código adaptado de https://github.com/eugenp/tutorials

public class BinaryTreePrinter <T extends No> {

    private ArvoreAVL tree;

    public BinaryTreePrinter(ArvoreAVL a) {
        this.tree = a;
    }

    private String traversePreOrder(NoAVL raiz) {

        if (raiz == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(raiz.getKey());

        String pointerRight = "└──";
        String pointerLeft = (raiz.getRight() != null) ? "├──" : "└──";

        traverseNodes(sb, "", pointerLeft, raiz.getLeft(), raiz.getRight() != null);
        traverseNodes(sb, "", pointerRight, raiz.getRight(), false);

        return sb.toString();
    }

    private void traverseNodes(StringBuilder sb, String padding, String pointer, NoAVL noAVL,
        boolean hasRightSibling) {

        if (noAVL != null) {

            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(noAVL.getKey());

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "└──";
            String pointerLeft = (noAVL.getRight() != null) ? "├──" : "└──";

            traverseNodes(sb, paddingForBoth, pointerLeft, noAVL.getLeft(), noAVL.getRight() != null);
            traverseNodes(sb, paddingForBoth, pointerRight, noAVL.getRight(), false);

        }

    }

    public void imprimir(PrintStream os) {
        os.print(traversePreOrder(tree.root));
    }

}