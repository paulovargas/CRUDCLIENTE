package com.paulovargas.api.config;

import com.paulovargas.api.entity.Client;
import com.paulovargas.api.entity.Menu;
import com.paulovargas.api.entity.UserType;
import com.paulovargas.api.repository.ClientRepository;
import com.paulovargas.api.repository.MenuRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class DefaultMenuConfig {

    @Bean
    public CommandLineRunner createDefaultMenus(
            MenuRepository menuRepository,
            ClientRepository clientRepository
    ) {
        return args -> {
            List<MenuSeed> menuSeeds = List.of(
                    new MenuSeed("Sistema", null, 0L, null),
                    new MenuSeed("Cadastros", null, 1L, null),
                    new MenuSeed("Movimento", null, 2L, null),
                    new MenuSeed("Relatorios", null, 3L, null),
                    new MenuSeed("Integracao", null, 4L, null),
                    new MenuSeed("Dashboard", "/backoffice/dashboard", 0L, 1L),
                    new MenuSeed("Usuarios", "/backoffice/usuarios", 1L, 1L),
                    new MenuSeed("Perfis de Acesso", "/backoffice/perfis-acesso", 2L, 1L),
                    new MenuSeed("Parametros Gerais", "/backoffice/parametros-gerais", 3L, 1L),
                    new MenuSeed("Auditoria", "/backoffice/auditoria", 4L, 1L),
                    new MenuSeed("Clientes", "/backoffice/clientes", 0L, 3L),
                    new MenuSeed("Fornecedores", "/backoffice/fornecedores", 1L, 3L),
                    new MenuSeed("Produtos", "/backoffice/produtos", 2L, 3L),
                    new MenuSeed("Servicos", "/backoffice/servicos", 3L, 3L),
                    new MenuSeed("Transportadoras", "/backoffice/transportadoras", 4L, 3L),
                    new MenuSeed("Condicoes de Pagamento", "/backoffice/condicoes-pagamento", 5L, 3L),
                    new MenuSeed("Centros de Custo", "/backoffice/centros-custo", 6L, 3L),
                    new MenuSeed("Pedidos de Venda", "/backoffice/pedidos-venda", 0L, 7L),
                    new MenuSeed("Compras", "/backoffice/compras", 1L, 7L),
                    new MenuSeed("Estoque", "/backoffice/estoque", 2L, 7L),
                    new MenuSeed("Faturamento", "/backoffice/faturamento", 3L, 7L),
                    new MenuSeed("Contas a Receber", "/backoffice/contas-receber", 4L, 7L),
                    new MenuSeed("Contas a Pagar", "/backoffice/contas-pagar", 5L, 7L),
                    new MenuSeed("Orcamentos", "/backoffice/orcamentos", 6L, 7L),
                    new MenuSeed("Vendas por Periodo", "/backoffice/relatorios/vendas-periodo", 0L, 16L),
                    new MenuSeed("Fluxo de Caixa", "/backoffice/relatorios/fluxo-caixa", 1L, 16L),
                    new MenuSeed("Posicao de Estoque", "/backoffice/relatorios/posicao-estoque", 2L, 16L),
                    new MenuSeed("Inadimplencia", "/backoffice/relatorios/inadimplencia", 3L, 16L),
                    new MenuSeed("DRE Gerencial", "/backoffice/relatorios/dre-gerencial", 4L, 16L),
                    new MenuSeed("Importacao de Dados", "/backoffice/integracao/importacao-dados", 0L, 13L),
                    new MenuSeed("Exportacao Fiscal", "/backoffice/integracao/exportacao-fiscal", 1L, 13L),
                    new MenuSeed("Notas Fiscais", "/backoffice/integracao/notas-fiscais", 2L, 13L),
                    new MenuSeed("Bancos", "/backoffice/integracao/bancos", 3L, 13L),
                    new MenuSeed("APIs Externas", "/backoffice/integracao/apis-externas", 4L, 13L)
            );

            Set<Menu> menus = new LinkedHashSet<>();

            for (MenuSeed seed : menuSeeds) {
                Menu menu = menuRepository.findByNameAndAddress(seed.name, seed.address)
                        .orElseGet(Menu::new);

                menu.setName(seed.name);
                menu.setAddress(seed.address);
                menu.setPosition(seed.position);
                menu.setRoot(seed.root);
                menus.add(menuRepository.save(menu));
            }

            for (Client admin : clientRepository.findByUserType(UserType.ADMIN)) {
                admin.getMenus().addAll(menus);
                clientRepository.save(admin);
            }
        };
    }

    private static class MenuSeed {
        private final String name;
        private final String address;
        private final Long position;
        private final Long root;

        private MenuSeed(String name, String address, Long position, Long root) {
            this.name = name;
            this.address = address;
            this.position = position;
            this.root = root;
        }
    }
}
