package br.com.automato;

import java.util.Scanner;

public class EmailAutomato {
	
	int cont; // contador
	static char letras[] = new char[26]; // lista das letras contidas no alfabeto
	static char numeros[] = new char[10]; // lista dos números contidos no alfabeto
	static char simbolos[] = {'@', '.'}; // lista dos caracteres especiais contidos no alfabeto
	char caracteres[]; // caracteres do email
	
	public static void main(String args[]) {
		
		// automato que reconhece emails no padrão "xxx@xxx.xxx"
		EmailAutomato emailAutomato = new EmailAutomato();;
		
		for (int i = 0; i < 26; i++) {
			EmailAutomato.letras[i] = (char) ('a' + i);
		}
		
		for (int n = 0; n < 10; n++) {
			EmailAutomato.numeros[n] = (char) ('0' + n);
		}
		
		// ler entrada
		Scanner scan = new Scanner(System.in);
		System.out.println("Insira o email: ");
		
		String email = scan.nextLine();
		emailAutomato.caracteres = email.toCharArray();
		
		emailAutomato.iniciar();
		
	}
	
	// verifica se o símbolo está presente no alfabeto
	public static boolean isInArray(char elem, char[] array) {
		for (int i = 0; i < array.length; i++) {
		    if (array[i] == elem) {
		        return true;
		    }
		}
		return false;
	}
	
	// inicia o automato
	public void iniciar() {
		cont = 0;
		q0(); // vai para estado q0
	}
	
	// estado q0 = aceita apenas letras e números
	public void q0() {
		
		if (EmailAutomato.isInArray(caracteres[cont], EmailAutomato.simbolos)){
			qError(); // palavra rejeitada
		} else if (EmailAutomato.isInArray(caracteres[cont], EmailAutomato.letras) == false && EmailAutomato.isInArray(caracteres[cont], EmailAutomato.numeros) == false){
			qError(); // palavra rejeitada
		} else {
			cont++;
			q1(); // vai para estado q1 ao receber uma letra ou número
		}
		
	}
	
	// estado q1 = aceita letras, números e caractéres especiais
	public void q1() {
		
		if (EmailAutomato.isInArray(caracteres[cont], EmailAutomato.simbolos) && caracteres[cont] == '.'){
			cont++;
			q0(); // volta para q0 ao receber um '.'
		} else if (EmailAutomato.isInArray(caracteres[cont], EmailAutomato.simbolos) && caracteres[cont] == '@'){
			cont++;
			q2(); // vai para q2 ao receber um '@'
		} else if (EmailAutomato.isInArray(caracteres[cont], EmailAutomato.letras) == false && EmailAutomato.isInArray(caracteres[cont], EmailAutomato.numeros) == false) {
			qError(); // palavra rejeitada
		} else {
			cont++;
			q1(); // continua no estado q1 ao receber uma letra ou número
		}
		
	}
	
	// estado q2 = aceita letras e números
	public void q2() {
		
		if (EmailAutomato.isInArray(caracteres[cont], EmailAutomato.simbolos)){
			qError(); // palavra rejeitada
		} else if (EmailAutomato.isInArray(caracteres[cont], EmailAutomato.letras) == false && EmailAutomato.isInArray(caracteres[cont], EmailAutomato.numeros) == false) {
			qError(); // palavra rejeitada
		} else {
			cont++;
			q3(); // vai para estado q3 ao receber uma letra ou número
		}		
		
	}
	
	// estado q3 = aceita letras, números e o caractere '.'
	public void q3() {

		if (EmailAutomato.isInArray(caracteres[cont], EmailAutomato.simbolos) && caracteres[cont] == '@'){
			qError(); // palavra rejeitada
		} else if (EmailAutomato.isInArray(caracteres[cont], EmailAutomato.letras) == false && EmailAutomato.isInArray(caracteres[cont], EmailAutomato.numeros) == false && EmailAutomato.isInArray(caracteres[cont], EmailAutomato.simbolos) == false) {
			qError(); // palavra rejeitada
		} else if (EmailAutomato.isInArray(caracteres[cont], EmailAutomato.letras) || EmailAutomato.isInArray(caracteres[cont], EmailAutomato.numeros)){
			cont++;
			q3(); // continua no estado q3 ao receber uma letra ou um número
		} else {
			cont++;
			qf(); // vai para o estado final (qf) ao receber o caractere '.'
		}
		
	}
	// estado final = aceita letras e números
	public void qf() {
		
		if (EmailAutomato.isInArray(caracteres[cont], EmailAutomato.simbolos)) {
			qError(); // palavra rejeitada
		} else if (EmailAutomato.isInArray(caracteres[cont], EmailAutomato.letras) == false && EmailAutomato.isInArray(caracteres[cont], EmailAutomato.numeros) == false) {
			qError(); // palavra rejeitada
		} else if (cont < caracteres.length-1){
			cont++;
			qf(); // continua no estado final ao receber uma letra ou número
		} else {
			qAceito(); // palavra aceita
		}
		
	}
	
	public void qAceito() {
		
		System.out.println("Email aceito");
		
	}
	
	public void qError() {
		
		System.out.println("Email rejeitado");
		
	}
	
}
