import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

interface MenuItem {
  nome: string;
  endereco: string | null;
  posicao?: number;
  raiz?: number;
}

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css'],
  imports: [CommonModule]
})
export class NavigationComponent implements OnInit {
  listaMenus: MenuItem[] = [
    { nome: 'Sistema', endereco: null, posicao: 0 },
    { nome: 'Cadastros', endereco: null, posicao: 1 },
    { nome: 'Movimento', endereco: null, posicao: 2 },
    { nome: 'Relatorios', endereco: null, posicao: 3 },
    { nome: 'Integracao', endereco: null, posicao: 4 },

    { nome: 'Dashboard', endereco: '/backoffice/dashboard', raiz: 1 },
    { nome: 'Usuarios', endereco: '/backoffice/usuarios', raiz: 1 },
    { nome: 'Perfis de Acesso', endereco: '/backoffice/perfis-acesso', raiz: 1 },
    { nome: 'Parametros Gerais', endereco: '/backoffice/parametros-gerais', raiz: 1 },
    { nome: 'Auditoria', endereco: '/backoffice/auditoria', raiz: 1 },

    { nome: 'Clientes', endereco: '/backoffice/clientes', raiz: 3 },
    { nome: 'Fornecedores', endereco: '/backoffice/fornecedores', raiz: 3 },
    { nome: 'Produtos', endereco: '/backoffice/produtos', raiz: 3 },
    { nome: 'Servicos', endereco: '/backoffice/servicos', raiz: 3 },
    { nome: 'Transportadoras', endereco: '/backoffice/transportadoras', raiz: 3 },
    { nome: 'Condicoes de Pagamento', endereco: '/backoffice/condicoes-pagamento', raiz: 3 },
    { nome: 'Centros de Custo', endereco: '/backoffice/centros-custo', raiz: 3 },

    { nome: 'Pedidos de Venda', endereco: '/backoffice/pedidos-venda', raiz: 7 },
    { nome: 'Compras', endereco: '/backoffice/compras', raiz: 7 },
    { nome: 'Estoque', endereco: '/backoffice/estoque', raiz: 7 },
    { nome: 'Faturamento', endereco: '/backoffice/faturamento', raiz: 7 },
    { nome: 'Contas a Receber', endereco: '/backoffice/contas-receber', raiz: 7 },
    { nome: 'Contas a Pagar', endereco: '/backoffice/contas-pagar', raiz: 7 },
    { nome: 'Orcamentos', endereco: '/backoffice/orcamentos', raiz: 7 },

    { nome: 'Vendas por Periodo', endereco: '/backoffice/relatorios/vendas-periodo', raiz: 16 },
    { nome: 'Fluxo de Caixa', endereco: '/backoffice/relatorios/fluxo-caixa', raiz: 16 },
    { nome: 'Posicao de Estoque', endereco: '/backoffice/relatorios/posicao-estoque', raiz: 16 },
    { nome: 'Inadimplencia', endereco: '/backoffice/relatorios/inadimplencia', raiz: 16 },
    { nome: 'DRE Gerencial', endereco: '/backoffice/relatorios/dre-gerencial', raiz: 16 },

    { nome: 'Importacao de Dados', endereco: '/backoffice/integracao/importacao-dados', raiz: 13 },
    { nome: 'Exportacao Fiscal', endereco: '/backoffice/integracao/exportacao-fiscal', raiz: 13 },
    { nome: 'Notas Fiscais', endereco: '/backoffice/integracao/notas-fiscais', raiz: 13 },
    { nome: 'Bancos', endereco: '/backoffice/integracao/bancos', raiz: 13 },
    { nome: 'APIs Externas', endereco: '/backoffice/integracao/apis-externas', raiz: 13 }
  ];

  constructor(private readonly router: Router) { }

  ngOnInit() {
  }

  abreModal(input: string): void {
    void this.router.navigate(['/backoffice', { outlets: { main: [input] } }]);
  }

  transformaEmRota(input: string): string {
    return input
      .toLowerCase()
      .normalize('NFD')
      .replace(/[\u0300-\u036f]/g, '')
      .replace(/[^a-z0-9]+/g, '-')
      .replace(/(^-|-$)/g, '');
  }

}
