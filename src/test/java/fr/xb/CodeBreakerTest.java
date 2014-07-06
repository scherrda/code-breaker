package fr.xb;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CodeBreakerTest {

    @Test
    public void should_return_a_plus_sign_for_an_exact_match() throws Exception {
        CodeBreaker codeBreaker = new CodeBreaker("1234");
        assertThat(codeBreaker.match("1999")).isEqualTo("+");
        assertThat(codeBreaker.match("9299")).isEqualTo("+");
        assertThat(codeBreaker.match("9939")).isEqualTo("+");
        assertThat(codeBreaker.match("9994")).isEqualTo("+");
    }

    @Test
    public void should_return_a_minus_sign_for_a_digit_match() throws Exception {
        CodeBreaker codeBreaker = new CodeBreaker("1234");
        assertThat(codeBreaker.match("9199")).isEqualTo("-");
        assertThat(codeBreaker.match("9949")).isEqualTo("-");
        assertThat(codeBreaker.match("2999")).isEqualTo("-");
    }

    @Test
    public void should_return_a_combination_of_minus_and_plus_sign_for_matches() throws Exception {
        CodeBreaker codeBreaker = new CodeBreaker("1234");
        assertThat(codeBreaker.match("1249")).isEqualTo("++-");
        assertThat(codeBreaker.match("9134")).isEqualTo("-++");
    }

    @Test
    public void should_return_4_plus_if_exact_matches() throws Exception {
        CodeBreaker codeBreaker = new CodeBreaker("1234");
        assertThat(codeBreaker.match("1234")).isEqualTo("++++");
    }
    @Test
    public void should_return_4_minus_if_digit_matches() throws Exception {
        CodeBreaker codeBreaker = new CodeBreaker("1234");
        assertThat(codeBreaker.match("4321")).isEqualTo("----");
    }


    @Test
    public void should_treat_exact_matches_prior_to_digit_matches() throws Exception {
        CodeBreaker codeBreaker = new CodeBreaker("1234");
        assertThat(codeBreaker.match("1111")).isEqualTo("+");
        assertThat(codeBreaker.match("2200")).isEqualTo("+");
        assertThat(codeBreaker.match("3033")).isEqualTo("+");
        assertThat(codeBreaker.match("4004")).isEqualTo("+");
    }

    @Test
    public void an_exact_match_digit_should_not_be_used_for_another_match() throws Exception {
        CodeBreaker codeBreaker = new CodeBreaker("1234");
        String match = codeBreaker.match("1111");
        assertThat(match).hasSize(1).isEqualTo("+");
        assertThat(codeBreaker.match("2200")).hasSize(1).isEqualTo("+");
        assertThat(codeBreaker.match("3033")).hasSize(1).isEqualTo("+");
        assertThat(codeBreaker.match("4004")).hasSize(1).isEqualTo("+");
    }
    @Test
    public void a_matched_secret_digit_should_not_be_used_for_another_match() throws Exception {
        CodeBreaker codeBreaker = new CodeBreaker("1234");
        assertThat(codeBreaker.match("9111")).hasSize(1).isEqualTo("-");
        assertThat(codeBreaker.match("2020")).hasSize(1).isEqualTo("-");
        assertThat(codeBreaker.match("3300")).hasSize(1).isEqualTo("-");
        assertThat(codeBreaker.match("4440")).hasSize(1).isEqualTo("-");
    }

    @Test
    public void can_find_redundant_digits() throws Exception {
        CodeBreaker codeBreaker = new CodeBreaker("1222");
        assertThat(codeBreaker.match("1222")).isEqualTo("++++");
    }
    @Test
    public void secret_can_start_with_zero() throws Exception {
        CodeBreaker codeBreaker = new CodeBreaker("0012");
        assertThat(codeBreaker.match("1222")).isEqualTo("-+");
        assertThat(codeBreaker.match("0012")).isEqualTo("++++");
    }


    @Test
    public void testing_different_use_cases() throws Exception {
        CodeBreaker codeBreaker = new CodeBreaker("1234");
        assertThat(codeBreaker.match("1245")).isEqualTo("++-");

        assertThat(codeBreaker.match("2002")).isEqualTo("-");

        assertThat(codeBreaker.match("2200")).isEqualTo("+");
    }

}
