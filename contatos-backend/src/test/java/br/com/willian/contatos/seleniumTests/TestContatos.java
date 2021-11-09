package br.com.willian.contatos.seleniumTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class TestContatos {

    private WebDriver driver;

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
    public void ListarAsTarefas() throws InterruptedException {
        driver.get("http://localhost:3000/");
        Thread.sleep(3000);
        assertTrue("Título da Página difere do esperado", driver.getTitle().contentEquals("Minhas Tarefas"));
    }

    /*TESTE PARA CADASTRAR UM NOVA TAREFA*/

    @Test
    public void RegisterTodo() throws InterruptedException {
        driver.get("http://localhost:3000/");
        Thread.sleep(3000);
        try{
            driver.findElement(By.xpath("/html/body/div/div[2]/input")).sendKeys("Lavar Louça" + Keys.ENTER);
            Thread.sleep(2000);
            assertEquals("Lavar Louça", driver.findElement(By.xpath("/html/body/div/div[3]/div/p")).getText());
        }
        finally {
            driver.quit();
        }
    }

    /*TESTE PARA DELETAR UMA TAREFA*/

    @Test
    public void DeleteTodo() throws InterruptedException {
        driver.get("http://localhost:3000/");
        Thread.sleep(3000);
        try{
            driver.findElement(By.xpath("/html/body/div/div[2]/input")).sendKeys("Lavar o Carro" + Keys.ENTER);
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div/div[3]/div/span")).click();
            Thread.sleep(2000);
        }
        finally {
            driver.quit();
        }
    }

    @Test
    public void ConcluiTarefa() throws InterruptedException {
        driver.get("http://localhost:3000/");
        Thread.sleep(3000);
        try{
            driver.findElement(By.xpath("/html/body/div/div[2]/input")).sendKeys("limpar a casa" + Keys.ENTER);
            driver.findElement(By.xpath("/html/body/div/div[2]/input")).sendKeys("finalizar o relatório" + Keys.ENTER);
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div/div[3]/div")).click();
            Thread.sleep(2000);
            assertEquals("limpar a casa", driver.findElement(By.xpath("/html/body/div/div[3]/div/p")).getText());
        }
        finally {
            driver.quit();
        }
    }


}