package elementos;

import org.openqa.selenium.By;

public class ElementosWeb {

    // Elementos privados
    private By campoPesquisa = By.name("q");
    private By resultadoJava = By.xpath("/html/body/div[7]/div/div[11]/div[2]/div/div/div[2]/div/div[2]/div[1]/div/div/div/div[2]/h2/span");
    private By resultadoCsharp = By.xpath("/html/body/div[7]/div/div[12]/div[2]/div/div/div[2]/div/div[2]/div[1]/div/div/div/div[2]/h2/span");

    // Elementos publicos

    public By getCampoPesquisa() {
        return campoPesquisa;
    }

    public By getResultadoJava() {
        return resultadoJava;
    }

    public By getResultadoCsharp() {
        return resultadoCsharp;
    }
}
