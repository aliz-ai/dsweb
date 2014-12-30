package com.doctusoft.dsw.client.comp.datatable;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "title")
public abstract class ColumnDescriptor<Item> implements Serializable {

	private String title;

}
