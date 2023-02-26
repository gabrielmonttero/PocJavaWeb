package steps;

import elementos.ElementosWeb;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import paginas.Metodos;
import paginas.Navegacoes;

import java.io.IOException;


public class Testes {

    Metodos mt = new Metodos();
    Navegacoes nv = new Navegacoes();
    ElementosWeb el = new ElementosWeb();


    @Dado("que eu acesse o site {string}")
    public void que_eu_acesse_o_site(String site) throws IOException {
        mt.abrirNavegador(site, "Chrome", "abro_o_navegador");

    }

    @Quando("realizo pesquisa java")
    public void realizo_pesquisa_java() throws IOException, InterruptedException {
        nv.realizarPesquisa("Java", "pesquiso_java");
    }

    @Quando("realizo pesquisa csharp")
    public void realizo_pesquisa_csharp() throws IOException, InterruptedException {
        nv.realizarPesquisa("CSharp", "pesquiso_csharp");

    }

    @Então("valido a pesquisa e evidencio java")
    public void valido_a_pesquisa_e_evidencio_java() throws IOException, InterruptedException {
        nv.validarEvidenciar(el.getResultadoJava(), "Java", "01_pesquisa_java", "valido_e_evidencio_a_pesquisa_java");
    }

    @Então("valido a pesquisa e evidencio csharp")
    public void valido_a_pesquisa_e_evidencio_csharp() throws IOException, InterruptedException {
        nv.validarEvidenciar(el.getResultadoCsharp(), "C Sharp", "02_pesquisa_csharp", "valido_e_evidencio_a_pesquisa_csharp");
    }

    @E("fecho o navegador")
    public void fecho_o_navegador() throws IOException {
        mt.fecharNavegador("fecha_o_navegaador");
    }
}
