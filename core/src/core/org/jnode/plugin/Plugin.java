/*
 * $Id$
 */
package org.jnode.plugin;

import org.jnode.system.BootLog;

/**
 * Abstract plugin class.
 * 
 * @author Ewout Prangsma (epr@users.sourceforge.net)
 */
public abstract class Plugin {

	/** The descriptor of this plugin */
	private final PluginDescriptor descriptor;
	/** Has this plugin been started? */
	private boolean started;

	/**
	 * Initialize a new instance
	 * 
	 * @param descriptor
	 */
	public Plugin(PluginDescriptor descriptor) {
		this.descriptor = descriptor;
		this.started = false;
		if (descriptor == null) {
			throw new IllegalArgumentException("descriptor cannot be null");
		}
	}

	/**
	 * Gets the descriptor of this plugin
	 * 
	 * @return The descriptor
	 */
	public final PluginDescriptor getDescriptor() {
		return descriptor;
	}

	/**
	 * Start this plugin
	 * 
	 * @throws PluginException
	 */
	final void start() throws PluginException {
		if (!started) {
			startPlugin();
			started = true;
		} else {
			BootLog.debug("Plugin " + descriptor.getId() + " already started");
		}
	}

	/**
	 * Stop this plugin.
	 * 
	 * @throws PluginException
	 */
	final void stop() throws PluginException {
		if (started) {
			stopPlugin();
			started = false;
		}
	}

	/**
	 * Is this plugin active. A plugin if active between a call to start and stop.
	 * 
	 * @see #start()
	 * @see #stop()
	 * @return boolean
	 */
	public final boolean isActive() {
		return started;
	}

	/**
	 * Actually start this plugin.
	 * 
	 * @throws PluginException
	 */
	protected abstract void startPlugin() throws PluginException;

	/**
	 * Actually start this plugin.
	 * 
	 * @throws PluginException
	 */
	protected abstract void stopPlugin() throws PluginException;
}
