package paginas;

import elementos.ElementosWeb;
import org.openqa.selenium.By;

import java.io.IOException;

public class Navegacoes {

    Metodos mt = new Metodos();
    ElementosWeb el = new ElementosWeb();
    public void realizarPesquisa(String pesquisa, String descricaoPasso) throws IOException, InterruptedException {
        mt.esperarClicavel(el.getCampoPesquisa(),"espero_campo_pesquisa_ficar_visivel");
        mt.pausa(10000,"espero_um_segundo");
        mt.escrever(el.getCampoPesquisa(), pesquisa, "escrevo_a_minha_pesquisa");
        mt.teclaEnter("teclo_enter");
    }

    public void validarEvidenciar(By elemento, String validarTexto, String nomePrint, String descricaoPasso) throws IOException, InterruptedException {
        mt.esperarClicavel(elemento, "espero_elemento_ficar_visivel");
        mt.validarTexto(elemento, validarTexto, "valido_texto");
        mt.pausa(1000,"espero_um_segundo");
        mt.printScreenGoogle(nomePrint);
    }

}
