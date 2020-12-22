package br.com.progiv.calculadoragorgeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    //objetos fomatadores de moeda:
    private static final NumberFormat currencyformat = NumberFormat.getCurrencyInstance();// define valor moeda ou tipo de acordo ccom linguagem do aparelho
    private double vrConta=0.0; //valor da conta enserida pelo usuario
    private double percent=0.15; //porcentagem inicial da gorgeta
    private TextView valorContaTextViwer; // mostrar o valor da conta
    private TextView valorTotalTextViwer;// valor total da conta
    private TextView valorGorgetaTextViwer;// valor da gorjeta
    private TextView porcentagemTextViwer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obter referencia para text viwer manipular via programação
        valorContaTextViwer=(TextView)findViewById(R.id.ValorConta);
        valorTotalTextViwer=(TextView)findViewById(R.id.valorTotal);
        valorGorgetaTextViwer=(TextView)findViewById(R.id.ValorGorgeta);
        porcentagemTextViwer=(TextView)findViewById(R.id.porcentagem);


        //tratamento zerar
        valorTotalTextViwer.setText(currencyformat.format( 0));
        valorGorgetaTextViwer.setText(currencyformat.format(0));
        //porcentagemTextViwer.setText(currencyformat.format(0));



        //configurando receptor Textwatcher de valorContaEdittext
        EditText valorContaEditText = (EditText)findViewById(R.id.ValorConta);
        valorContaEditText.addTextChangedListener(ValorContaEditWatcher);

        //configurar receptor seekbar
        SeekBar percentBar = (SeekBar)findViewById(R.id.porcentagemBar);
        percentBar.setOnSeekBarChangeListener(SeekBarListener);

    }

    private void calcular(){
        try {
            Double gorgeta = vrConta *percent;
            Double total =vrConta+gorgeta;

            valorTotalTextViwer.setText(currencyformat.format(total));
            valorGorgetaTextViwer.setText(currencyformat.format(gorgeta));




        }catch (Exception Erro){
        String y = Erro.getMessage();
        }
    }

    private final SeekBar.OnSeekBarChangeListener SeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            percent=progress/100.0;
            porcentagemTextViwer.setText(String.valueOf(progress)+("%"));
            calcular();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private final TextWatcher ValorContaEditWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {

                vrConta = Double.parseDouble(s.toString());
                valorTotalTextViwer.setText(currencyformat.format(vrConta));
            }catch(Exception ex){
                String y = ex.getMessage();
            }

        }

        @Override
        public void afterTextChanged(Editable s) {



        }
    };
}