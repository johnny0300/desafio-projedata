import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.math.RoundingMode;

public class Principal {

    
    public static void main(String[] args) {

        // 3.1 - Preencher tabela de funcionários
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 - Remover funcionário João
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

        // 3.3 - Imprimir informações de funcionários
        System.out.println("\n- Funcionários -");
        imprimirFuncionarios(funcionarios);

        // 3.4 - Aumento de 10% nos salários
        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.10")).setScale(2, RoundingMode.HALF_UP)));

        System.out.println("\n- Funcionários: Após aumento de 10% -");
        imprimirFuncionarios(funcionarios);


        // 3.5 - Agrupar em um MAP
        // 3.6 – Imprimir os funcionários agrupados por função
        // Não conheço o método MAP, vou prosseguir alguns estudos a respeito
        

        // 3.7 - Imprimir funcionários que fazem aniversário no mês 10 e 12
        List<Funcionario> aniversariooutedec = funcionarios.stream() 
            .filter (f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
            .collect(Collectors.toList());

        System.out.println("\n- Aniversariantes de Outubro e Dezembro -");
        imprimirFuncionarios(aniversariooutedec);

    }

    public static void imprimirFuncionarios(List<Funcionario> funcionarios) {
    for (Funcionario f : funcionarios) {
        System.out.printf(
            "Nome: %s | Nascimento: %s | Função: %s | Salário: %s%n",
            f.getNome(),
            f.getDataNascimento().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            f.getFuncao(),
            String.format("%,.2f", f.getSalario())
        );
    }
}

}