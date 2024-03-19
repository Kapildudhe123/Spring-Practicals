package com.rays.ctl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.DropDownList;
import com.rays.common.ORSResponse;
import com.rays.dto.UserDTO;
import com.rays.form.LoginForm;
import com.rays.form.UserForm;
import com.rays.service.UserServiceInte;

@RestController
@RequestMapping(value = "user")
public class UserCtl {

	@Autowired
	UserServiceInte service;

	@PostMapping("add")
	public ORSResponse add(@RequestBody @Valid UserForm form, BindingResult br) {

		ORSResponse res = validate(br);

		if (!res.isSuccess()) {
			return res;

		}

		UserDTO dto = new UserDTO();
		dto.setId(form.getId());
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setLoginId(form.getLoginId());
		dto.setPassword(form.getPassword());
		if (dto.getId() != null && dto.getId() > 0) {
			service.update(dto); 
			res.addMessage("data updated succesfully");

		} else {
			service.add(dto);
			res.addMessage("user added succesfully");

		}
		return res;

	}

	@PostMapping("preload")
	public ORSResponse preload(@RequestBody UserForm form) {
		ORSResponse res = new ORSResponse();
		UserDTO dto = new UserDTO();
		dto.setLastName(form.getLastName());
		List<DropDownList> rolelist = service.search(dto, 0, 0);

		res.addResult("rolelist", rolelist);

		return res;

	}

//	@PostMapping("update")
//	public ORSResponse update(@RequestBody UserForm form) {
//		ORSResponse res = new ORSResponse();
//		UserDTO dto = new UserDTO();
//		// dto.setId(form.getId());
//		dto.setFirstName(form.getFirstName());
//		dto.setLastName(form.getLastName());
//		dto.setLoginId(form.getLoginId());
//		dto.setPassword(form.getPassword());
//		service.update(dto);
//		res.addMessage("user update");
//		return res;
//	}

	@GetMapping("delete/{id}")
	public ORSResponse delete(@PathVariable long id) {
		ORSResponse res = new ORSResponse();
		UserDTO dto = new UserDTO();
		service.delete(id);
		res.addMessage("data delete");
		return res;

	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {
		ORSResponse res = new ORSResponse();

		UserDTO dto = service.findbyId(id);

		if (dto != null) {
			res.addData(dto);
		}

		return res;

	}

	@PostMapping("search/{pageNo}")
	public ORSResponse search(@RequestBody UserForm form, @PathVariable int pageNo) {

		ORSResponse res = new ORSResponse();
		UserDTO dto = new UserDTO();
		dto.setId(form.getId());
		dto.setFirstName(form.getFirstName());
		List list = service.search(dto, pageNo, 5);
		// System.out.println(list.size());
		if (list.size() == 0) {
			res.addMessage("NO RECORD FOUND...!!!");
		} else {
			res.addData(list);
		}

		return res;

	}

	public ORSResponse validate(BindingResult bindingResult) {

		ORSResponse res = new ORSResponse(true);

		System.out.println("inside the validate method of baseCtl");
		if (bindingResult.hasErrors()) {
			System.out.println("BaseCtl ki validate ke error block me");
			res.setSuccess(false);

			Map<String, String> errors = new HashMap<String, String>();

			List<FieldError> list = bindingResult.getFieldErrors();
			// Lambda expression Java 8 feature
			list.forEach(e -> {
				errors.put(e.getField(), e.getDefaultMessage());
				System.out.println("Field :: " + e.getField() + "  Message :: " + e.getDefaultMessage());
			});
			/* res.addInputErrors(errors); */
			res.addInputError(errors);
		}
		return res;
	}

	@PostMapping("login")
	public ORSResponse login(@RequestBody @Valid LoginForm form, BindingResult bindingResult) throws Exception {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		UserDTO dto = service.authenticate(form.getLoginId(), form.getPassword());
		if (dto == null) {
			res.addMessage("Invalid ID or Password");
			res.setSuccess(false);
		} else {

			res.addMessage("user is valid");
			res.setSuccess(true);

		}
		return res;
	}
}
