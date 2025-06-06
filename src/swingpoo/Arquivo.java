package swingpoo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Arquivo {

	public static void gerarArquivoTabela
	 (String caminho, ArrayList<EPessoa> oListaPessoa) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(caminho));
		
		String linha = "";
		
		for (EPessoa oEPessoa : oListaPessoa) {
			buffWrite.append(linha + oEPessoa.getCPF() + " | " 
		+ oEPessoa.getNome() + " | " + oEPessoa.getDataNasc()
					+ " | " + oEPessoa.getSexo() + "\n");
		}

		buffWrite.close();
	}

}