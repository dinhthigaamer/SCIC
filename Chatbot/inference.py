# inference.py
import torch
import time
from utils import format_input

def model_response(model, tokenizer, age, gender, marital_status, cbt_technique, context, max_new_tokens=100):
    input_text = format_input(age, gender, marital_status, cbt_technique, context)
    inputs = tokenizer(input_text, return_tensors="pt").to(model.device)

    start_time = time.time()
    with torch.no_grad():
        output_tokens = model.generate(
            **inputs,
            max_new_tokens=max_new_tokens,
            do_sample=False,
            num_beams=3,
            eos_token_id=tokenizer.eos_token_id
        )

    response_text = tokenizer.decode(output_tokens[0], skip_special_tokens=True)
    time_taken = time.time() - start_time
    response = response_text[len(input_text):].strip()

    return response, time_taken
