package com.chiletel.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * <h2>Descripción:</h2>
 * clase encagada de tipar mensaje de repuesta para put, post, delete
 * @author Brayan Baquero
 *
 */

@AllArgsConstructor
@Getter
@Setter
public class MessageOk {
	private String message;
}
