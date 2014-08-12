package com.doctusoft.dsw.client.mvp;

/*
 * #%L
 * dsweb
 * %%
 * Copyright (C) 2014 Doctusoft Ltd.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import java.io.Serializable;

import com.doctusoft.dsw.mvp.client.ViewOf;

/**
 * Usign the ActualPresenter type parameter might be an overkill for type-safety. It introduces a lot of typing with
 * subtypes (see {@link AbstractPresenter} or {@link Place})
 */
public interface Presenter<ActualPresenter extends Presenter<ActualPresenter>> extends Serializable {

	public void start(Place<ActualPresenter> place);

	public void onUnload();

	public ViewOf<ActualPresenter> getView();
}
