package de.keag.lager.core.fehler;

public class EmailBeanDbLagerException extends LagerException{
		public EmailBeanDbLagerException(Fehler fehler) {
			super(fehler);
		}
	}