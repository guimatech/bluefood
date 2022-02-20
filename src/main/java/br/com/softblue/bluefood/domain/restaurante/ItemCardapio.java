package br.com.softblue.bluefood.domain.restaurante;

import br.com.softblue.bluefood.infrastructure.web.validator.UploadConstraint;
import br.com.softblue.bluefood.util.FileType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "item_cardapio")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemCardapio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O nome não pode ser vázio")
    @Size(max = 50)
    private String nome;

    @NotBlank(message = "A categoria não pode ser vázia")
    @Size(max = 25)
    private String categoria;

    @NotBlank(message = "A descrição não pode ser vázia")
    @Size(max = 80)
    private String descricao;

    @Size(max = 50)
    private String imagem;

    @NotNull(message = "O preço não pode ser vázio")
    @Min(0)
    private BigDecimal preco;

    @NotNull
    private Boolean destaque;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    @UploadConstraint(acceptedTypes = FileType.PNG, message = "O arquivo não é um arquivo de imagem válido")
    private transient MultipartFile imagemFile;

    public void setImagemFileName() {
        if (getId() == null)
            throw new IllegalStateException("O objeto precisa primeiro ser criado");

        this.imagem = String.format("%04d-comida.%s", getId(), FileType.of(imagemFile.getContentType()).getExtension());
    }
}
