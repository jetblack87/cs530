package org.dataart.view.imprt;

import org.dataart.model.Data;
import org.dataart.view.ASubpanel;

@SuppressWarnings("serial")
public abstract class AImportSubpanel extends ASubpanel {
	public abstract Data importData();
}
