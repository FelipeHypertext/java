package Exercise8;
/*Classe especial para o carregamento de dados, ao qual inclui automaticamente
 *Private para cada campo
 *Getters públicos
 *Final para todos os atributos por implicitamente não gerar subclasses
 *Construtor
 *MentalNote - Classes com finals podem receber interfaces */
public record Funcionario(String nome, Float espacoDisco) {
}
