package com.refresh.pos.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.refresh.pos.R;
import com.refresh.pos.core.Inventory;
import com.refresh.pos.core.Product;
import com.refresh.pos.database.NoDaoSetException;

public class ShowListActivity extends Activity {

	private Inventory inventory;
//	ArrayList<HashMap<String, String>> productList;
	List<Product> productList;
	HashMap<String, String> map;
	ArrayList<HashMap<String, String>> stockList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showlist);

		try {
			inventory = Inventory.getInstance();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
		
		
//		// return list of product not map   TODO: FIX IT @mapfap
		productList = inventory.getProductCatalog().getAllProduct();
		for (Product product : productList) {
			map = new HashMap<String, String>();
	        map.put("_id", product.getId()+"");
 	        map.put("name", product.getName());
 	        map.put("barcode", product.getBarcode());
 	        map.put("sale_price", product.getSalePrice()+"");
 	        stockList.add(map);
		}

		// listView1
		ListView lisView1 = (ListView) findViewById(R.id.listView1);

		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(ShowListActivity.this, stockList,
				R.layout.activity_column, new String[] { "_id", "name",
						"barcode","sale_price" }, new int[] { R.id.ColProductID,
						R.id.ColName, R.id.ColBarcode, R.id.ColSalePrice });
		lisView1.setAdapter(sAdap);

		final Button addProductButton = (Button) findViewById(R.id.addNewProduct);

		addProductButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent newActivity = new Intent(ShowListActivity.this,AddActivity.class);
				startActivity(newActivity);
			}
		});

	}

}