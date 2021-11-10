package br.com.willian.contatos.seleniumTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class TestContatos {

    private WebDriver driver;

    private String retornaNomeAleatorio(){
        Random random = new Random();
        int numeroInteiroAleatorio_0_a_1000 = random.nextInt(1000);
        return "SeleniumTestes" + numeroInteiroAleatorio_0_a_1000;
    }

    private String retornaEmailAleatorio(){
        Random random = new Random();
        int numeroInteiroAleatorio_0_a_1000 = random.nextInt(1000);
        return "SeleniumTestes" + numeroInteiroAleatorio_0_a_1000 + "@hotmail.com";
    }

    private String retornaTelefoneAleatorio(){
        Random random = new Random();
        Integer telefone = random.nextInt((999999999 - 111111111) + 1) + 100000;
        return Integer.toString(telefone);
    }

    @Before
    public void setUp() throws Exception{
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\SUPORTE\\Documents\\workspace\\JAVA\\UNIJOBS\\images\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() throws Exception{
        driver.quit();
    }

    /*TESTE PARA ABRIR NAVEGADOR E VERIFICAR SE O SITE ABRE*/

    @Test
    public void ListarOsContatos() throws InterruptedException {
        driver.get("http://localhost:3000/");
        Thread.sleep(3000);
        assertTrue("Título da Página difere do esperado", driver.getTitle().contentEquals("Lista de Contatos"));
    }

    /*TESTE PARA CADASTRAR UM NOVA TAREFA*/

    @Test
    public void RegisterContact() throws InterruptedException {
        driver.get("http://localhost:3000/");
        Thread.sleep(3000);
        try{
            driver.findElement(By.xpath("/html/body/div/div/form/input[1]")).sendKeys(retornaNomeAleatorio());
            driver.findElement(By.xpath("/html/body/div/div/form/input[2]")).sendKeys(retornaEmailAleatorio());
            driver.findElement(By.xpath("/html/body/div/div/form/input[3]")).sendKeys(retornaTelefoneAleatorio());
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div/div/form/button")).click();
            Thread.sleep(2000);
            driver.switchTo().alert().accept();
            Thread.sleep(2000);
        }
        finally {
            driver.quit();
        }
    }

    /*TESTE PARA DELETAR UMA TAREFA*/

    @Test
    public void DeleteContact() throws InterruptedException {
        driver.get("http://localhost:3000/");
        Thread.sleep(3000);
        try{
            driver.findElement(By.xpath("/html/body/div/div/table/tbody/tr/td[4]/button[2]")).click();
            Thread.sleep(3000);
            driver.switchTo().alert().accept();
            Thread.sleep(3000);
        }
        finally {
            driver.quit();
        }
    }

    @Test
    public void UpdateContact() throws InterruptedException {
        driver.get("http://localhost:3000/");
        Thread.sleep(3000);
        try{
            driver.findElement(By.xpath("/html/body/div/div/table/tbody/tr/td[4]/button[1]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div/div/form/input[2]")).sendKeys(retornaEmailAleatorio());
            driver.findElement(By.xpath("/html/body/div/div/form/input[3]")).sendKeys(retornaTelefoneAleatorio());
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div/div/form/button")).click();
            Thread.sleep(2000);
            driver.switchTo().alert().accept();
            Thread.sleep(2000);
        }
        finally {
            driver.quit();
        }
    }

}