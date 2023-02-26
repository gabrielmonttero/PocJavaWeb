package paginas;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class Metodos {

    WebDriver driver;

    /**
     * Abrir navegador e site
     *
     * @author Gabriel M Pereira
     *
     */
    public void abrirNavegador(String site, String navegador, String descricaoPasso) throws IOException {

        try {
            if (navegador == "Chrome" || navegador == "Firefox") {
                if (navegador == "Chrome") {
                    System.setProperty("webdriver.chrome.driver", "./driver/chrome_driver/chromedriver.exe");
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(false);
                    driver = new ChromeDriver(options);
                    driver.get(site);
                    driver.manage().window().maximize();
                } else if (navegador == "Firefox") {
                    System.setProperty("webdriver.gecko.driver", "./driver/gecko_driver/geckodriver.exe");
                    FirefoxOptions options = new FirefoxOptions();
                    options.setHeadless(true);
                    driver = new FirefoxDriver(options);
                    driver.get(site);
                    driver.manage().window().maximize();
                }
            } else {
                System.out.println("Opcao_invalida_escolha_Chrome_ou_Firefox");
            }
        } catch (Exception e) {
            printScreenErros("erro_ao_tentar_" + descricaoPasso);
            Assert.fail(LocalDateTime.now() + "erro_ao_tentar_" + descricaoPasso);
        }
    }

    /**
     * Escrever
     *
     * @author Gabriel M Pereira
     *
     */
    public void escrever(By elemento, String texto, String descricaoPasso) throws IOException {
        try {
            driver.findElement(elemento).sendKeys(texto);
        } catch (Exception e) {
            printScreenErros("erro_ao_tentar_" + descricaoPasso);
            Assert.fail(LocalDateTime.now() + "erro_ao_tentar_" + descricaoPasso);
        }
    }

    /**
     * Clicar
     *
     * @author Gabriel M Pereira
     *
     */
    public void clicar(By elemento, String descricaoPasso) throws IOException {
        try {
            driver.findElement(elemento).click();
        } catch (Exception e) {
            printScreenErros("erro_ao_tentar_" + descricaoPasso);
            Assert.fail(LocalDateTime.now() + "erro_ao_tentar_" + descricaoPasso);
        }
    }

    /**
     * Print Screen Google
     *
     * @author Gabriel M Pereira
     *
     */
    public void printScreenGoogle(String nomePrint) throws IOException {
        TakesScreenshot print = ((TakesScreenshot) driver);
        File SrcFile = print.getScreenshotAs(OutputType.FILE);
        File DestFile = new File("./evidencias/01_Google/" + nomePrint + ".png");
        FileUtils.copyFile(SrcFile, DestFile);
    }

    /**
     * Print Screen de Erros
     *
     * @author Gabriel M Pereira
     *
     */
    public void printScreenErros(String descricaoPasso) throws IOException {
        TakesScreenshot print = ((TakesScreenshot) driver);
        if (print == null) {
            System.out.println("****erro****");
            return;
        }
        File SrcFile = print.getScreenshotAs(OutputType.FILE);
        File DestFile = new File("./evidencias/erros/" + descricaoPasso + ".png");
        FileUtils.copyFile(SrcFile, DestFile);
    }

    /**
     * Fechar Navegador
     *
     * @author Gabriel M Pereira
     *
     */
    public void fecharNavegador(String descricaoPasso) throws IOException {
        try {
            driver.quit();
        } catch (Exception e) {
            printScreenErros("erro_ao_tentar_" + descricaoPasso);
            Assert.fail(LocalDateTime.now() + "erro_ao_tentar_" + descricaoPasso);
        }
    }

    /**
     * Duplo Click
     *
     * @author Gabriel M Pereira
     *
     */
    public void duploCliqueNoElemento(By elemento, String descricaoPasso) throws IOException {
        long TIMEOUT = 60;
        Actions action = new Actions(driver);
        try {
            driver.manage().timeouts().pageLoadTimeout(TIMEOUT, TimeUnit.SECONDS);
            action.doubleClick(
                    new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(elemento)));
        } catch (Exception e) {
            printScreenErros("erro_ao_tentar_" + descricaoPasso);
            Assert.fail(LocalDateTime.now() + "erro_ao_tentar_" + descricaoPasso);
        }
    }

    /**
     * Selecionar Combo na Posicao
     *
     * @author Gabriel M Pereira
     *
     */
    public void selecionarComboPosicao(By elemento, int posicao, String descricaoPasso) throws IOException {
        try {
            WebElement webElement = driver.findElement(elemento);
            Select combo = new Select(webElement);
            combo.selectByIndex(posicao);
        } catch (Exception e) {
            printScreenErros("erro_ao_tentar_" + descricaoPasso);
            Assert.fail(LocalDateTime.now() + "erro_ao_tentar_" + descricaoPasso);
        }
    }

    /**
     * Esperar ser clicavel
     *
     * @author Gabriel M Pereira
     *
     */
    public void esperarClicavel(By elemento, String descricaoPasso) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.elementToBeClickable(elemento));
        } catch (Exception e) {
            printScreenErros("erro_ao_tentar_" + descricaoPasso);
            Assert.fail(LocalDateTime.now() + "erro_ao_tentar_" + descricaoPasso);
        }
    }

    /**
     * Validar Texto
     *
     * @author Gabriel M Pereira
     *
     */
    public void validarTexto(By elemento, String texto, String descricaoPasso) throws IOException {
        try {
            String msg = driver.findElement(elemento).getText();
            assertEquals(texto, msg);
        } catch (Exception e) {
            printScreenErros("erro_ao_tentar_" + descricaoPasso);
            Assert.fail(LocalDateTime.now() + "erro_ao_tentar_" + descricaoPasso);
        }
    }

    /**
     * Pausa
     *
     * @author Gabriel M Pereira
     *
     */
    public void pausa(int tempo, String descricaoPasso) throws InterruptedException, IOException {
        try {
            Thread.sleep(tempo);
        } catch (Exception e) {
            printScreenErros("erro_ao_tentar_" + descricaoPasso);
            Assert.fail(LocalDateTime.now() + "erro_ao_tentar_" + descricaoPasso);
        }
    }

    /**
     * Passar o Mouse no Elemento Sem Clicar
     *
     * @author Gabriel M Pereira
     *
     */
    public void passarMouse(By elemento, String descricaoPasso) throws IOException {
        try {
            Actions action = new Actions(driver);
            WebElement passarMouse = driver.findElement(elemento);
            action.moveToElement(passarMouse).perform();
        } catch (Exception e) {
            printScreenErros("erro_ao_tentar_" + descricaoPasso);
            Assert.fail(LocalDateTime.now() + "erro_ao_tentar_" + descricaoPasso);
        }
    }

    /**
     * Pressionar tecla Enter
     *
     * @author Gabriel M Pereira
     *
     */
    public void teclaEnter(String descricaoPasso) throws IOException {
        try {
            Actions action = new Actions(driver);
            action.sendKeys(Keys.ENTER).perform();
        } catch (Exception e) {
            printScreenErros("erro_ao_tentar_" + descricaoPasso);
            Assert.fail(LocalDateTime.now() + "erro_ao_tentar_" + descricaoPasso);
        }
    }

    /**
     * Clicar e segurar, mover e soltar com mouse
     *
     * @author Gabriel M Pereira
     *
     */
    public void moverElemento(By elementoOrigem, By elementoDestino, String descricaoPasso) throws IOException {
        try {
            Actions action = new Actions(driver);
            WebElement origem = driver.findElement(elementoOrigem);
            WebElement destino = driver.findElement(elementoDestino);
            action.dragAndDrop(origem, destino).build().perform();
        } catch (Exception e) {
            printScreenErros("erro_ao_tentar_" + descricaoPasso);
            Assert.fail(LocalDateTime.now() + "erro_ao_tentar_" + descricaoPasso);
        }
    }

    /**
     * teclaPageUp
     *
     * @author Gabriel M Pereira
     *
     */
    public void teclaPageUp(String descricaoPasso) throws IOException {
        try {
            Actions action = new Actions(driver);
            action.sendKeys(Keys.PAGE_UP).perform();
        } catch (Exception e) {
            printScreenErros("erro_ao_tentar_" + descricaoPasso);
            Assert.fail(LocalDateTime.now() + "erro_ao_tentar_" + descricaoPasso);
        }
    }

    /**
     * teclaPageDw
     *
     * @author Gabriel M Pereira
     *
     */
    public void teclaPageDw(String descricaoPasso) throws IOException {
        try {
            Actions action = new Actions(driver);
            action.sendKeys(Keys.PAGE_DOWN).perform();
        } catch (Exception e) {
            printScreenErros("erro_ao_tentar_" + descricaoPasso);
            Assert.fail(LocalDateTime.now() + "erro_ao_tentar_" + descricaoPasso);
        }
    }

    /**
     * botaoVoltar
     *
     * @author Gabriel M Pereira
     *
     */
    public void botaoVoltar(String descricaoPasso) throws IOException {
        try {
            driver.navigate().back();
        } catch (Exception e) {
            printScreenErros("erro_ao_tentar_" + descricaoPasso);
            Assert.fail(LocalDateTime.now() + "erro_ao_tentar_" + descricaoPasso);
        }
    }

    /**
     * botaoAtualizar
     *
     * @author Gabriel M Pereira
     *
     */
    public void botaoAtualizar(String descricaoPasso) throws IOException {
        try {
            driver.navigate().refresh();
        } catch (Exception e) {
            printScreenErros("erro_ao_tentar_" + descricaoPasso);
            Assert.fail(LocalDateTime.now() + "erro_ao_tentar_" + descricaoPasso);
        }
    }

    /**
     * teclaBackSpace
     *
     * @author Gabriel M Pereira
     *
     */
    public void teclaBackSpace(String descricaoPasso) throws IOException {
        try {
            Actions action = new Actions(driver);
            action.sendKeys(Keys.BACK_SPACE).perform();
        } catch (Exception e) {
            printScreenErros("erro_ao_tentar_" + descricaoPasso);
            Assert.fail(LocalDateTime.now() + "erro_ao_tentar_" + descricaoPasso);
        }
    }

    /**
     * limparTexto
     *
     * @author Gabriel M Pereira
     *
     */
    public void limparTexto(By elemento, String descricaoPasso) throws IOException {
        try {
            driver.findElement(elemento).clear();
        } catch (Exception e) {
            printScreenErros("erro_ao_tentar_" + descricaoPasso);
            Assert.fail(LocalDateTime.now() + "erro_ao_tentar_" + descricaoPasso);
        }
    }

    /**
     * limparTextoAlternativo
     *
     * @author Gabriel M Pereira
     *
     */
    public void limparTextoAlternativo(By elemento, String descricaoPasso) throws IOException {
        try {
            driver.findElement(elemento).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        } catch (Exception e) {
            printScreenErros("erro_ao_tentar_" + descricaoPasso);
            Assert.fail(LocalDateTime.now() + "erro_ao_tentar_" + descricaoPasso);
        }
    }
}

