from model_loader import load_model
from inference import model_response
from data.sample_inputs import sample_inputs
import user_config

tokenizer = None
model = None


def init():
    global tokenizer, model
    model_path = "mental-health-counsellor"
    tokenizer, model = load_model(model_path)
    
    ''' 
    If do not have a local model, load through huggingface
    '''
    # model_path = "khaihoang004/mental-health-counselor"
    # tokenizer, model = load_model(model_path)
    
    print("Model loaded")
    
def run_demo():
    model_responses = []

    # For testing: read the input text in data/sample_inputs.py
    for input_text in sample_inputs:
        response, time_taken = model_response(
            model, tokenizer,
            age=user_config.age,
            gender=user_config.gender,
            marital_status=user_config.marital_status,
            cbt_technique=user_config.cbt_technique,
            context=input_text
        )
        
        model_response = response.split("User:")[0].strip()
        print("User:", input_text)
        print("AI Response:", model_response)
        print(f"Time taken: {time_taken:.2f} seconds\n")
        model_responses.append(response)

def main():
    init()
    run_demo()

if __name__ == "__main__":
    main()
