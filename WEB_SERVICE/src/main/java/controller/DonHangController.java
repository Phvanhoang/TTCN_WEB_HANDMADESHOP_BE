package controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import model.DonHang;
import model.DonHang_MatHang;
import model.TaiKhoan;
import service.DonHangService;
import utils.GetTaiKhoanFromTokenService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class DonHangController {
	@Autowired
	private DonHangService donHangService;
	
	@Autowired
	private GetTaiKhoanFromTokenService getTaiKhoanFromTokenService;
	
	@PreAuthorize("hasAnyAuthority({'ROLE_ADMIN', 'ROLE_USER'})")
	@PostMapping(value = "/authorized/don_hang/create", consumes = MediaType.APPLICATION_JSON_VALUE)
//	@PostMapping("/don_hang/create")
	public ResponseEntity<Void> createDonHang(@RequestHeader("Authorization") String token, @RequestBody net.minidev.json.JSONObject jObject){
		
		JSONObject jsonObject = new JSONObject(jObject);
		DonHang donHang = new DonHang();
		try {
			Gson gson = new Gson();
			donHang = gson.fromJson(jObject.toString(), DonHang.class);
			TaiKhoan taiKhoan = getTaiKhoanFromTokenService.getTaiKhoan(token);
			donHang.setNguoiDung(taiKhoan.getNguoiDung());
//			donHang.setUpdatedBy(taiKhoan);
			try {
				donHangService.createDonHang(donHang);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}