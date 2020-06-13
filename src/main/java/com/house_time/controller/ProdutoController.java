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

import com.house_time.repositorios.ProdutoRepositorio;

import com.house_time.infra.FotoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepositorio produtos;

	@Autowired
	private FotoService fotoService;

	@GetMapping
	public ModelAndView listar() {

		ModelAndView modelAndView = new ModelAndView("produto/lista-produtos");

		modelAndView.addObject("produtos", produtos.findAll());

		return modelAndView;
	}

	@GetMapping("/Apple")
	public ModelAndView listarApple() {

		ModelAndView modelAndView = new ModelAndView("produto/lista-produtos");

		modelAndView.addObject("produtos", produtos.findByFabricante("Apple"));

		return modelAndView;
	}
	
	@GetMapping("/Diesel")
	public ModelAndView listarDiesel() {

		ModelAndView modelAndView = new ModelAndView("produto/lista-produtos");

		modelAndView.addObject("produtos", produtos.findByFabricante("Diesel"));

		return modelAndView;
	}
	@GetMapping("/Xiaomi")
	public ModelAndView listarXiaomi() {

		ModelAndView modelAndView = new ModelAndView("produto/lista-produtos");

		modelAndView.addObject("produtos", produtos.findByFabricante("Xiaomi"));

		return modelAndView;
	}
	@GetMapping("/Fossil")
	public ModelAndView listarFossil() {

		ModelAndView modelAndView = new ModelAndView("produto/lista-produtos");

		modelAndView.addObject("produtos", produtos.findByFabricante("Fossil"));

		return modelAndView;
	}
	@GetMapping("/Mountblanc")
	public ModelAndView listarMountblanc() {

		ModelAndView modelAndView = new ModelAndView("produto/lista-produtos");

		modelAndView.addObject("produtos", produtos.findByFabricante("Mountblanc"));

		return modelAndView;
	}
	@GetMapping("/Sansung")
	public ModelAndView listarSangung() {

		ModelAndView modelAndView = new ModelAndView("produto/lista-produtos");

		modelAndView.addObject("produtos", produtos.findByFabricante("Sansung"));

		return modelAndView;
	}
	
	
	@GetMapping("/novo")
	public ModelAndView novo(Produto produto) {

		ModelAndView modelAndView = new ModelAndView("produto/Cadastroproduto1");

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
	
	
	@GetMapping("/imagem/{nome:.*}")
	public @ResponseBody byte[] recuperarFoto(@PathVariable String nome) throws IOException {
		
		return fotoService.recuperarFoto(nome);
	}

	@GetMapping("/apple")
	public String apple() {
		return "produto/apple";
	}

	@GetMapping("/samsung")
	public String sansung() {
		return "produto/samsung";
	}

	@GetMapping("/diesel")
	public String diesel() {
		return "produto/diesel";
	}

	@GetMapping("/fossil")
	public String fossil() {
		return "produto/fossil";
	}

	@GetMapping("/montblanc")
	public String montblanc() {
		return "produto/montblanc";
	}

	@GetMapping("/xiaomi")
	public String xiaomi() {
		return "produto/xiaomi";
	}
}
