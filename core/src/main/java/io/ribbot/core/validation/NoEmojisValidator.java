package io.ribbot.core.validation;

import com.cameronprater.emoji.EmojiManager;
import io.ribbot.core.CustomEmoji;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoEmojisValidator implements ConstraintValidator<NoEmojis, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintContext) {
        if (s == null) {
            return true;
        }
        Matcher matcher = Pattern.compile(CustomEmoji.REGEX).matcher(s);
        return EmojiManager.containsEmoji(s) || matcher.find();
    }
}