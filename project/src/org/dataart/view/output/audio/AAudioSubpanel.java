package org.dataart.view.output.audio;

import org.dataart.view.ASubpanel;

@SuppressWarnings("serial")
public abstract class AAudioSubpanel extends ASubpanel {
	public abstract long play();
	public abstract void pause();
	public abstract void stop();
}
