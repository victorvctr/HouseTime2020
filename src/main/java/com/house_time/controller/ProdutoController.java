package com.house_time.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.house_time.models.Produto;
import com.house_time.repositorios.FornecedorRepositorio;
import com.house_time.repositorios.ProdutoRepositorio;

import com.house_time.infra.FotoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepositorio produtos;
	
	@Autowired
	private FornecedorRepositorio fornecedores;

	@Autowired
	private FotoService fotoService;

	@GetMapping
	public ModelAndView listar() {

		ModelAndView modelAndView = new ModelAndView("produto/lista-produtos");

		modelAndView.addObject("produtos", produtos.findAll());

		return modelAndView;
	}

	@GetMapping("/novo")
	public ModelAndView novo(Produto produto) {

		ModelAndView modelAndView = new ModelAndView("produto/Cadastroproduto1");
		
		modelAndView.addObject("fornecedores", fornecedores.findAll());

		modelAndView.addObject(produto);

		return modelAndView;
	}

	@GetMapping("editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {

		return novo(produtos.getOne(id));
	}

	@PostMapping(value = "excluir/{id}")
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {

		produtos.deleteById(id);

		attributes.addFlashAttribute("mensagem", "produto excluido com sucesso!!");

		return "redirect:/produto";
	}

	@PostMapping("/salvar")
	public ModelAndView Cadastrar(@RequestParam("file") MultipartFile file, @Valid Produto produto,
			BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			System.out.println(result.getErrorCount());
			System.out.println(result.getAllErrors());
			return novo(produto);
		}

		if (!file.isEmpty()) {

			if (produto.getUrl_imagem() != null && !produto.getUrl_imagem().isEmpty()) {
				fotoService.removerFoto(produto.getUrl_imagem());
			}

			String arquivoFoto = fotoService.doUpload(file, produto);

			if (arquivoFoto.equals("erro")) {
				attributes.addFlashAttribute("mensagem", "Problemas para salvar a imagem do produto!!");
				return novo(produto);
			} else {
				produto.setUrl_imagem(arquivoFoto);
			}
		}

		produtos.save(produto);

		attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!!");
		return new ModelAndView("redirect:/produto");

	}
	
	@GetMapping("/detalhes/{id}")
	public ModelAndView detalhes(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("produto/detalhes");
		
		Produto p = produtos.getOne(id);

		modelAndView.addObject(p);

		return modelAndView;

	}
	
	
	@GetMapping("/imagem/{nome:.*}")
	public @ResponseBody byte[] recuperarFoto(@PathVariable String nome) throws IOException {
		
		return fotoService.recuperarFoto(nome);
	}

	@GetMapping(value={"/Apple","/apple"})
	public ModelAndView apple() {
		ModelAndView modelAndView = new ModelAndView("produto/apple");

		modelAndView.addObject("produtos", produtos.findByFabricanteAndAtivoTrue("Apple"));

		return modelAndView;

	}

	@GetMapping(value={"/Samsung","/samsung"})
	public ModelAndView sansung() {
		ModelAndView modelAndView = new ModelAndView("produto/samsung");

		modelAndView.addObject("produtos", produtos.findByFabricanteAndAtivoTrue("Samsung"));

		return modelAndView;

	}

	@GetMapping(value={"/Diesel","/diesel"})
	public ModelAndView diesel() {
		ModelAndView modelAndView = new ModelAndView("produto/diesel");

		modelAndView.addObject("produtos", produtos.findByFabricanteAndAtivoTrue("Diesel"));

		return modelAndView;
	}

	@GetMapping(value={"/Fossil","/fossil"})
	public ModelAndView fossil() {
		ModelAndView modelAndView = new ModelAndView("produto/fossil");

		modelAndView.addObject("produtos", produtos.findByFabricanteAndAtivoTrue("Fossil"));

		return modelAndView;

	}

	@GetMapping(value={"/Montblanc","/montblanc"})
	public ModelAndView montblanc() {
		ModelAndView modelAndView = new ModelAndView("produto/montblanc");

		modelAndView.addObject("produtos", produtos.findByFabricanteAndAtivoTrue("Montblanc"));

		return modelAndView;

	}

	@GetMapping(value={"/Xiaomi","/xiaomi"})
	public ModelAndView xiaomi() {
		ModelAndView modelAndView = new ModelAndView("produto/xiaomi");

		modelAndView.addObject("produtos", produtos.findByFabricanteAndAtivoTrue("Xiaomi"));

		return modelAndView;

	}
}
