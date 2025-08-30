import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.math.RoundingMode;
import java.util.Comparator;
import java.time.Period;
import java.util.Collections;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


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
        
        // 3.7 - Não existe

        // 3.8 - Imprimir funcionários que fazem aniversário no mês 10 e 12
        List<Funcionario> aniversariooutedec = funcionarios.stream() 
            .filter (f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
            .collect(Collectors.toList());

        System.out.println("\n- Aniversariantes de Outubro e Dezembro -");
        imprimirFuncionarios(aniversariooutedec);

        // 3.9 - Imprimir funcionário com a maior idade
        Funcionario maioridade = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);
        if (maioridade != null) {
            int idade = Period.between(maioridade.getDataNascimento(), LocalDate.now()).getYears();
        
            System.out.println("\nFuncionário mais velho: " + maioridade.getNome() + " | Idade: " + idade + " anos");
        }

        // 3.10 - Ordenar funcionários por ordem alfabética
        System.out.println("\n- Funcionários em ordem alfabética -");
        List<Funcionario> ordemalfabetica = new ArrayList<>(funcionarios);
        ordemalfabetica.sort(Comparator.comparing(f -> f.getNome()));
        imprimirFuncionarios(ordemalfabetica);

        // 3.11 - Somar todos os salários
        BigDecimal valorTotal = funcionarios.stream()
            .map(Funcionario::getSalario)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        DecimalFormat df = new DecimalFormat("#,##0.00");

        System.out.println("\nTotal dos salários: R$ " + df.format(valorTotal));

        // 3.12 - Somar todos os salários
        BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");
        DecimalFormat dfSemDecimal = new DecimalFormat("#,##0");

        System.out.println("\n- Salários mínimos por funcionário -");
        funcionarios.forEach(f -> {
            BigDecimal qtd = f.getSalario().divide(SALARIO_MINIMO, 0, RoundingMode.HALF_DOWN);
            if (qtd.intValue() == 1) {
                System.out.println("Nome:" + f.getNome() + " | Quantidade: " + dfSemDecimal.format(qtd) + " salário mínimo");
            } else {
                System.out.println("Nome:" + f.getNome() + " | Quantidade: " + dfSemDecimal.format(qtd) + " salários mínimos");
            }
        });

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