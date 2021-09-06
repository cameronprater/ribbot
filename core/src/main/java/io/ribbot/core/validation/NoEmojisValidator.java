package io.ribbot.core.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cameronprater.emoji.EmojiManager;

import io.ribbot.core.CustomEmoji;

@ApplicationScoped
public class NoEmojisValidator implements ConstraintValidator<NoEmojis, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintContext) {
        if (s == null) {
            return true;
        }
        Matcher matcher = Pattern.compile(CustomEmoji.REGEX).matcher(s);
        return !EmojiManager.containsEmoji(s) && !matcher.find();
    }
}
